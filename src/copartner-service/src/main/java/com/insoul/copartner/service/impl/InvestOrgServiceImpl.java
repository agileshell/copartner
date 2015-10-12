package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IInvestOrgDAO;
import com.insoul.copartner.dao.criteria.InvestOrgCriteria;
import com.insoul.copartner.domain.InvestOrg;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IInvestOrgService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.InvestOrgDetailVO;
import com.insoul.copartner.vo.InvestOrgVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.InvestOrgListRequest;

@Service
public class InvestOrgServiceImpl extends BaseServiceImpl implements IInvestOrgService {

    @Resource
    private IInvestOrgDAO investOrgDAO;

    @Override
    public Pagination<InvestOrgVO> listInvestOrgs(InvestOrgListRequest requestData) {
        InvestOrgCriteria criteria = new InvestOrgCriteria();
        criteria.setName(requestData.getKeyword());
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);

        Long count = investOrgDAO.countInvestOrg(criteria);
        List<InvestOrg> list = investOrgDAO.queryInvestOrg(criteria);
        List<InvestOrgVO> investOrgVOs = new ArrayList<InvestOrgVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (InvestOrg investOrg : list) {
                InvestOrgVO vo = new InvestOrgVO();
                vo.setId(investOrg.getId());
                vo.setName(investOrg.getName());
                vo.setLogo(CDNUtil.getFullPath(investOrg.getLogo()));
                vo.setHardware(investOrg.getHardware());
                vo.setSpecials(investOrg.getSpecials());
                vo.setCreated(investOrg.getCreated());

                investOrgVOs.add(vo);
            }
        }
        return new Pagination<InvestOrgVO>(investOrgVOs, count);
    }

    @Override
    public InvestOrgDetailVO getInvestOrg(Long investOrgId) throws CException {
        InvestOrg investOrg = investOrgDAO.get(investOrgId);
        if (null == investOrg) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.INVEST_ORG_NOT_EXIST);
        }

        InvestOrgDetailVO detail = new InvestOrgDetailVO();
        detail.setId(investOrg.getId());
        detail.setName(investOrg.getName());
        detail.setLogo(CDNUtil.getFullPath(investOrg.getLogo()));
        detail.setHardware(investOrg.getHardware());
        detail.setSpecials(investOrg.getSpecials());
        detail.setContent(investOrg.getContent());
        detail.setCreated(investOrg.getCreated());

        return detail;
    }
}
