package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.CampaignDetailVO;
import com.insoul.copartner.vo.CampaignVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.CampaignListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月25日 下午11:04:13
 */
public interface CampaignService {

    Pagination<CampaignVO> listCampaigns(CampaignListRequest requestData);
    
    CampaignDetailVO getDetail(Long id) throws CException;
}