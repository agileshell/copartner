package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.INewsDao;
import com.insoul.copartner.dao.criteria.NewsCriteria;
import com.insoul.copartner.domain.News;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.INewsService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.NewsDetailVO;
import com.insoul.copartner.vo.NewsVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.NewsListRequest;

@Service
public class NewsServiceImpl extends BaseServiceImpl implements INewsService {

    @Resource
    private INewsDao newsDao;

    @Override
    public Pagination<NewsVO> listNews(NewsListRequest requestData) {
        List<NewsVO> newsVOs = new ArrayList<NewsVO>();

        NewsCriteria newsCriteria = new NewsCriteria();
        newsCriteria.setType(requestData.getType());
        newsCriteria.setOffset(requestData.getOffset());
        newsCriteria.setLimit(requestData.getLimit());
        newsCriteria.setStatus(new String[] { "active" });
        newsCriteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData
                .getFrom()) : null);
        newsCriteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo())
                : null);

        Long count = newsDao.countNews(newsCriteria);
        List<News> newses = newsDao.queryNews(newsCriteria);
        for (News news : newses) {
            NewsVO newsVO = new NewsVO();
            newsVO.setNewsId(news.getId());
            newsVO.setTitle(news.getTitle());
            newsVO.setType(news.getType());
            newsVO.setSynopsis(news.getSynopsis());
            newsVO.setCoverImg(CDNUtil.getFullPath(news.getCoverImg()));
            newsVO.setClicks(news.getClicks());
            newsVO.setCreated(news.getCreated());

            newsVOs.add(newsVO);
        }

        return new Pagination<NewsVO>(newsVOs, count);
    }

    @Override
    public NewsDetailVO getNews(Long newsId) throws CException {
        News news = newsDao.get(newsId);
        if (null == news) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CONTENT_NOT_EXIST);
        }

        NewsDetailVO newsVO = new NewsDetailVO();
        newsVO.setNewsId(news.getId());
        newsVO.setTitle(news.getTitle());
        newsVO.setType(news.getType());
        newsVO.setSynopsis(news.getSynopsis());
        newsVO.setCoverImg(CDNUtil.getFullPath(news.getCoverImg()));
        newsVO.setArticle(news.getArticle());
        newsVO.setClicks(news.getClicks());
        newsVO.setCreated(news.getCreated());

        return newsVO;
    }

}
