package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.EntityType;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IContentDao;
import com.insoul.copartner.dao.IUserFavouritesDao;
import com.insoul.copartner.dao.criteria.ContentCriteria;
import com.insoul.copartner.domain.Content;
import com.insoul.copartner.domain.UserFavourites;
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

    @Resource
    private IUserFavouritesDao userFavouritesDao;

    @Override
    public Pagination<ContentVO> listContents(ContentListRequest requestData) {
        List<ContentVO> contentVOs = new ArrayList<ContentVO>();

        ContentCriteria contentCriteria = new ContentCriteria();
        contentCriteria.setOffset(requestData.getOffset());
        contentCriteria.setLimit(requestData.getLimit());
        contentCriteria.setStatus(new String[] {"active"});
        contentCriteria.setType(requestData.getType() == null ? 0 : requestData.getType());
        contentCriteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData
                .getFrom()) : null);
        contentCriteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo())
                : null);
        contentCriteria.setTitle(requestData.getKeyword());

        Long count = contentDao.countContent(contentCriteria);
        List<Content> contents = contentDao.queryContent(contentCriteria);
        for (Content content : contents) {
            ContentVO contentVO = new ContentVO();
            contentVO.setContentId(content.getId());
            contentVO.setTitle(content.getTitle());
            contentVO.setSynopsis(content.getSynopsis());
            contentVO.setCoverImg(CDNUtil.getFullPath(content.getCoverImg()));
            contentVO.setClicks(content.getClicks());
            contentVO.setCreated(content.getCreated());
            contentVO.setType(content.getType() == null ? 0 : content.getType());
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
        contentVO.setSynopsis(content.getSynopsis());
        contentVO.setCoverImg(CDNUtil.getFullPath(content.getCoverImg()));
        contentVO.setArticle(content.getArticle());
        contentVO.setClicks(content.getClicks());
        contentVO.setCreated(content.getCreated());
        contentVO.setType(content.getType() == null ? 0 : content.getType());
        long userId = getUserId();
        UserFavourites userFavourites =
                userFavouritesDao.getByUserIdAndEntity(userId, contentId, EntityType.CONTENT.getValue());
        contentVO.setIsliked(userFavourites != null);

        return contentVO;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void likeOrUnlikeContent(Long contentId) {
        long userId = getUserId();
        UserFavourites userFavourites =
                userFavouritesDao.getByUserIdAndEntity(userId, contentId, EntityType.CONTENT.getValue());
        if (userFavourites == null) {
            userFavourites = new UserFavourites();
            userFavourites.setUserId(userId);
            userFavourites.setEntityId(contentId);
            userFavourites.setEntityType(EntityType.CONTENT.getValue());
            userFavourites.setCreated(new Date());

            userFavouritesDao.save(userFavourites);
        } else {
            userFavouritesDao.delete(userFavourites);
        }
    }

}
