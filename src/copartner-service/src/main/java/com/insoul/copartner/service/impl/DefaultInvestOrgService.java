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

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 下午12:19:19
 */
@Service
public class DefaultInvestOrgService extends BaseServiceImpl implements IInvestOrgService {

    @Resource
    private IInvestOrgDAO investOrgDAO;

    @Override
    public Pagination<InvestOrgVO> listInvestOrgs(InvestOrgListRequest requestData) {
        InvestOrgCriteria criteria = new InvestOrgCriteria();
        criteria.setName(requestData.getKeyword());
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData
                .getFrom()) : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo())
                : null);
        
        Long count = investOrgDAO.countInvestOrg(criteria);
        List<InvestOrg> list = investOrgDAO.queryInvestOrg(criteria);
        List<InvestOrgVO> investOrgVOs = new ArrayList<InvestOrgVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (InvestOrg p : list) {
                InvestOrgVO vo = new InvestOrgVO();
                vo.setId(p.getId());
                vo.setCreated(p.getCreated());
                vo.setHardware(p.getHardware());
                vo.setId(p.getId());
                vo.setName(p.getName());
                vo.setSpecials(p.getSpecials());
                vo.setLogo(CDNUtil.getFullPath(p.getLogo()));
                investOrgVOs.add(vo);
            }
        }
        return new Pagination<InvestOrgVO>(investOrgVOs, count);
    }

    @Override
    public InvestOrgDetailVO getInvestOrg(Long investOrgId) throws CException {
        InvestOrg io = investOrgDAO.get(investOrgId);
        if (null == io) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.INVEST_ORG_NOT_EXIST);
        }
        InvestOrgDetailVO detail = new InvestOrgDetailVO();
        detail.setId(io.getId());
        detail.setContent(io.getContent());
        detail.setCreated(io.getCreated());
        detail.setHardware(io.getHardware());
        detail.setId(io.getId());
        detail.setName(io.getName());
        detail.setSpecials(io.getSpecials());
        detail.setLogo(CDNUtil.getFullPath(io.getLogo()));
        return detail;
    }
}
