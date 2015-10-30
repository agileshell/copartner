package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IServiceArchDAO;
import com.insoul.copartner.dao.criteria.InvestOrgCriteria;
import com.insoul.copartner.domain.ServiceArch;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IServiceOrgService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ServiceArchVO;
import com.insoul.copartner.vo.request.InvestOrgListRequest;

@Service
public class ServiceOrgServiceImpl extends BaseServiceImpl implements IServiceOrgService {

    @Resource
    private IServiceArchDAO serviceArchDAO;

    @Override
    public Pagination<ServiceArchVO> listServiceOrgs(InvestOrgListRequest requestData) {
        InvestOrgCriteria criteria = new InvestOrgCriteria();
        criteria.setName(requestData.getKeyword());
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);

        Long count = serviceArchDAO.countServiceArch(criteria);
        List<ServiceArch> list = serviceArchDAO.queryServiceArch(criteria);
        List<ServiceArchVO> serviceArchVOs = new ArrayList<ServiceArchVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ServiceArch serviceArch : list) {
                ServiceArchVO vo = new ServiceArchVO();
                vo.setId(serviceArch.getId());
                vo.setName(serviceArch.getName());
                vo.setIcon(CDNUtil.getFullPath(serviceArch.getIcon()));
                vo.setCreated(serviceArch.getCreated());

                serviceArchVOs.add(vo);
            }
        }

        return new Pagination<ServiceArchVO>(serviceArchVOs, count);
    }

    @Override
    public ServiceArchVO getServiceOrg(Long ServiceOrgId) throws CException {
        ServiceArch serviceArch = serviceArchDAO.get(ServiceOrgId);
        if (null == serviceArch) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.SERVICE_ORG_NOT_EXIST);
        }

        ServiceArchVO detail = new ServiceArchVO();
        detail.setId(serviceArch.getId());
        detail.setName(serviceArch.getName());
        detail.setIcon(CDNUtil.getFullPath(serviceArch.getIcon()));
        detail.setDescription(serviceArch.getDescription());
        detail.setCreated(serviceArch.getCreated());

        return detail;
    }

}
