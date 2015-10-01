package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IPioneerParkDAO;
import com.insoul.copartner.dao.criteria.PioneerParkCriteria;
import com.insoul.copartner.domain.PioneerPark;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IPioneerParkService;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.PioneerParkDetailVO;
import com.insoul.copartner.vo.PioneerParkVO;
import com.insoul.copartner.vo.request.PoineerParkListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 上午11:56:23
 */
@Service
public class DefaultPioneerParkService extends BaseServiceImpl implements IPioneerParkService {

    @Resource
    private IPioneerParkDAO pioneerParkDAO;
    
    @Override
    public Pagination<PioneerParkVO> listPioneerParks(PoineerParkListRequest requestData) {
        PioneerParkCriteria criteria = new PioneerParkCriteria();
        criteria.setName(requestData.getKeyword());
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData
                .getFrom()) : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo())
                : null);
        criteria.setProvince(requestData.getProvince());
        Long count = pioneerParkDAO.countPioneerPark(criteria);
        List<PioneerPark> list = pioneerParkDAO.queryPioneerPark(criteria);
        List<PioneerParkVO> pioneerParkVOs = new ArrayList<PioneerParkVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (PioneerPark p : list) {
                PioneerParkVO vo = new PioneerParkVO();
                vo.setId(p.getId());
                vo.setAddress(p.getAddress());
                vo.setAddressDetail(p.getAddressDetail());
                vo.setArea(p.getArea());
                vo.setCity(p.getCity());
                vo.setCreated(p.getCreated());
                vo.setId(vo.getId());
                vo.setLatitude(p.getLatitude());
                vo.setLongitude(p.getLongitude());
                vo.setName(p.getName());
                vo.setProvince(p.getProvince());
                pioneerParkVOs.add(vo);
            }
        }
        return new Pagination<PioneerParkVO>(pioneerParkVOs, count);
    }

    @Override
    public PioneerParkDetailVO getPioneerPark(Long pioneerParkId) throws CException {
        PioneerPark pioneerPark = pioneerParkDAO.get(pioneerParkId);
        if (null == pioneerPark) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.PIONEER_PARK_NOT_EXIST);
        }
        PioneerParkDetailVO detail = new PioneerParkDetailVO();
        detail.setId(pioneerPark.getId());
        detail.setAddress(pioneerPark.getAddress());
        detail.setAddressDetail(pioneerPark.getAddressDetail());
        detail.setArea(pioneerPark.getArea());
        detail.setCity(pioneerPark.getCity());
        detail.setContent(pioneerPark.getContent());
        detail.setCreated(pioneerPark.getCreated());
        detail.setId(pioneerPark.getId());
        detail.setLatitude(pioneerPark.getLatitude());
        detail.setLongitude(pioneerPark.getLongitude());
        detail.setName(pioneerPark.getName());
        detail.setProvince(pioneerPark.getProvince());
        return detail;
    }
}