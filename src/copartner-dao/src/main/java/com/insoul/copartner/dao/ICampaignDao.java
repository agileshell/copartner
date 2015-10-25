package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.CampaignCriteria;
import com.insoul.copartner.domain.Campaign;

public interface ICampaignDao extends IBaseDao<Campaign, Long> {

    List<Campaign> queryCampaign(CampaignCriteria criteria);

    Long countCampaign(CampaignCriteria criteria);
}
