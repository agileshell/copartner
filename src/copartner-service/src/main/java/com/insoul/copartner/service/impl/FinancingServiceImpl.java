package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.DemandStatus;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IFinancingDao;
import com.insoul.copartner.dao.IFinancingPhaseDao;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.ILocationDao;
import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.dao.criteria.FinancingCriteria;
import com.insoul.copartner.domain.Financing;
import com.insoul.copartner.domain.FinancingPhase;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Location;
import com.insoul.copartner.domain.TeamSize;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IFinancingService;
import com.insoul.copartner.util.ContentUtil;
import com.insoul.copartner.vo.FinancingVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.FinancingAddRequest;
import com.insoul.copartner.vo.request.FinancingListRequest;

@Service
public class FinancingServiceImpl extends BaseServiceImpl implements IFinancingService {

    @Resource
    private IFinancingDao financingDao;

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IFinancingPhaseDao financingPhaseDao;

    @Resource
    private IIndustryDomainDao industryDomainDao;

    @Resource
    private ITeamSizeDao teamSizeDao;

    @Override
    public Pagination<FinancingVO> listFinancings(FinancingListRequest requestData) {
        FinancingCriteria criteria = new FinancingCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setUserId(requestData.getUserId());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);

        if (null != requestData.getUserId() && requestData.getUserId().equals(getUserId())) {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue(), DemandStatus.INACTIVE.getValue() });
        } else {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue() });
        }

        List<Financing> financings = financingDao.queryFinancing(criteria);
        Long count = financingDao.countFinancing(criteria);
        return new Pagination<FinancingVO>(formatFinancings(financings), count);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void createFinancing(FinancingAddRequest requestData) throws CException {
        long userId = getUserId();
        Financing financing = new Financing();

        FinancingPhase financingPhase = financingPhaseDao.get(requestData.getFinancingPhaseId());
        if (null == financingPhase) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.FINANCING_PHASE_NOT_EXIST);
        }
        financing.setFinancingPhaseId(requestData.getFinancingPhaseId());

        IndustryDomain industryDomain = industryDomainDao.get(requestData.getIndustryDomainId());
        if (null == industryDomain) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.INDUSTRY_DOMAIN_NOT_EXIST);
        }
        financing.setIndustryDomainId(requestData.getIndustryDomainId());

        TeamSize teamSize = teamSizeDao.get(requestData.getTeamSizeId());
        if (null == teamSize) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.TEAM_SIZE_NOT_EXIST);
        }
        financing.setTeamSizeId(requestData.getTeamSizeId());

        Location location = locationDao.get(requestData.getLocationId());
        if (null == location) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.LOCATION_NOT_EXIST);
        }
        // 缓存地区全名
        StringBuilder fullLocation = new StringBuilder();
        fullLocation.append(location.getName());
        Location parentLocation = locationDao.get(location.getParentId());
        if (null != parentLocation) {
            fullLocation.append("|").append(parentLocation.getName());
        }
        financing.setFullLocation(fullLocation.toString());
        financing.setLocationId(requestData.getLocationId());

        financing.setUserId(userId);
        financing.setProjectName(requestData.getProjectName());
        financing.setHasBusinessRegistered(requestData.getHasBusinessRegistered());
        financing.setAdvantage(requestData.getAdvantage());
        financing.setContent(requestData.getContent());
        financing.setFunding(requestData.getFunding());
        financing.setContactPerson(requestData.getContactPerson());
        financing.setContact(requestData.getContact());
        financing.setCreated(new Date());

        financingDao.save(financing);
    }

    private List<FinancingVO> formatFinancings(List<Financing> financings) {
        List<FinancingVO> financingVOs = new ArrayList<FinancingVO>();
        if (financings == null || financings.isEmpty()) {
            return financingVOs;
        }

        Set<Long> financingIds = new HashSet<Long>();
        for (Financing financing : financings) {
            financingIds.add(financing.getId());
        }

        List<IndustryDomain> industryDomains = industryDomainDao.findAll();
        Map<Long, String> domainIdMapName = new HashMap<Long, String>();
        for (IndustryDomain industryDomain : industryDomains) {
            domainIdMapName.put(industryDomain.getId(), industryDomain.getName());
        }
        List<TeamSize> teamSizes = teamSizeDao.findAll();
        Map<Long, String> teamSizeIdMapName = new HashMap<Long, String>();
        for (TeamSize teamSize : teamSizes) {
            teamSizeIdMapName.put(teamSize.getId(), teamSize.getName());
        }
        List<FinancingPhase> financingPhases = financingPhaseDao.findAll();
        Map<Long, String> phaseIdMapName = new HashMap<Long, String>();
        for (FinancingPhase financingPhase : financingPhases) {
            phaseIdMapName.put(financingPhase.getId(), financingPhase.getName());
        }

        for (Financing financing : financings) {
            FinancingVO financingVO = new FinancingVO();
            financingVO.setProjectName(financing.getProjectName());
            financingVO.setLocation(financing.getFullLocation());

            financingVO.setFinancingPhase(phaseIdMapName.get(financing.getFinancingPhaseId()));
            financingVO.setIndustryDomain(domainIdMapName.get(financing.getIndustryDomainId()));
            financingVO.setTeamSize(teamSizeIdMapName.get(financing.getTeamSizeId()));
            financingVO.setContent(ContentUtil.splitAndFilterString(financing.getContent(), 80));
            financingVO.setCreated(financing.getCreated());

            financingVOs.add(financingVO);
        }

        return financingVOs;
    }

}
