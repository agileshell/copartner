package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.NewsDetailVO;
import com.insoul.copartner.vo.NewsVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.NewsListRequest;

public interface INewsService {

    Pagination<NewsVO> listNews(NewsListRequest requestData);

    NewsDetailVO getNews(Long newsId) throws CException;

    void likeOrUnlikeNews(Long newsId);
}
