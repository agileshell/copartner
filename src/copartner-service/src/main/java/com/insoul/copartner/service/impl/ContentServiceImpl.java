package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IContentDao;
import com.insoul.copartner.dao.criteria.ContentCriteria;
import com.insoul.copartner.domain.Content;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IContentService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.ContentDetailVO;
import com.insoul.copartner.vo.ContentVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContentListRequest;

@Service
public class ContentServiceImpl extends BaseServiceImpl implements IContentService {

    @Resource
    private IContentDao contentDao;

    @Override
    public Pagination<ContentVO> listContents(ContentListRequest requestData) {
        List<ContentVO> contentVOs = new ArrayList<ContentVO>();

        ContentCriteria contentCriteria = new ContentCriteria();
        contentCriteria.setType(requestData.getType());
        contentCriteria.setOffset(requestData.getOffset());
        contentCriteria.setLimit(requestData.getLimit());
        contentCriteria.setStatus(new String[] { "active" });
        contentCriteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData
                .getFrom()) : null);
        contentCriteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo())
                : null);

        Long count = contentDao.countContent(contentCriteria);
        List<Content> contents = contentDao.queryContent(contentCriteria);
        for (Content content : contents) {
            ContentVO contentVO = new ContentVO();
            contentVO.setContentId(content.getId());
            contentVO.setTitle(content.getTitle());
            contentVO.setType(content.getType());
            contentVO.setSynopsis(content.getSynopsis());
            contentVO.setCoverImg(CDNUtil.getFullPath(content.getCoverImg()));
            contentVO.setClicks(content.getClicks());
            contentVO.setCreated(content.getCreated());

            contentVOs.add(contentVO);
        }

        return new Pagination<ContentVO>(contentVOs, count);
    }

    @Override
    public ContentDetailVO getContent(Long contentId) throws CException {
        Content content = contentDao.get(contentId);
        if (null == content) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CONTENT_NOT_EXIST);
        }

        ContentDetailVO contentVO = new ContentDetailVO();
        contentVO.setContentId(content.getId());
        contentVO.setTitle(content.getTitle());
        contentVO.setType(content.getType());
        contentVO.setSynopsis(content.getSynopsis());
        contentVO.setCoverImg(CDNUtil.getFullPath(content.getCoverImg()));
        contentVO.setArticle(content.getArticle());
        contentVO.setClicks(content.getClicks());
        contentVO.setCreated(content.getCreated());

        return contentVO;
    }

}
