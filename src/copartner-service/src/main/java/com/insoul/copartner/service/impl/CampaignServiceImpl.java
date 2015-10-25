package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.ICampaignDao;
import com.insoul.copartner.dao.criteria.CampaignCriteria;
import com.insoul.copartner.domain.Campaign;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.CampaignService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.CampaignDetailVO;
import com.insoul.copartner.vo.CampaignVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.CampaignListRequest;

@Service
public class CampaignServiceImpl extends BaseServiceImpl implements CampaignService {

    @Resource
    private ICampaignDao campaignDao;

    @Override
    public Pagination<CampaignVO> listCampaigns(CampaignListRequest requestData) {
        List<CampaignVO> campaignVOs = new ArrayList<CampaignVO>();

        CampaignCriteria criteria = new CampaignCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        if (StringUtils.isBlank(requestData.getStatus())) {
            criteria.setStatus(new String[] { "active" });
        } else {
            criteria.setStatus(new String[] { requestData.getStatus() });
        }

        Long count = campaignDao.countCampaign(criteria);
        List<Campaign> campaigns = campaignDao.queryCampaign(criteria);
        for (Campaign campaign : campaigns) {
            CampaignVO contentVO = new CampaignVO();
            contentVO.setId(campaign.getId());
            contentVO.setTitle(campaign.getTitle());
            contentVO.setCoverImg(CDNUtil.getFullPath(campaign.getCoverImg()));
            contentVO.setCreated(campaign.getCreated());

            campaignVOs.add(contentVO);
        }

        return new Pagination<CampaignVO>(campaignVOs, count);
    }

    @Override
    public CampaignDetailVO getDetail(Long id) throws CException {
        Campaign campaign = campaignDao.get(id);
        if (null == campaign) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CAMPAIGN_NOT_EXIST);
        }

        CampaignDetailVO campaignVO = new CampaignDetailVO();
        campaignVO.setId(campaign.getId());
        campaignVO.setTitle(campaign.getTitle());
        campaignVO.setCoverImg(CDNUtil.getFullPath(campaign.getCoverImg()));
        campaignVO.setCreated(campaign.getCreated());
        campaignVO.setContent(campaign.getContent());
        campaignVO.setStatus(campaign.getStatus());
        return campaignVO;
    }
}
