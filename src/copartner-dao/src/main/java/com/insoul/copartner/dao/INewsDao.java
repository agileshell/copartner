package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.NewsCriteria;
import com.insoul.copartner.domain.News;

public interface INewsDao extends IBaseDao<News, Long> {

    List<News> queryNews(NewsCriteria criteria);

    Long countNews(NewsCriteria criteria);

    List<News> findByNewsIds(Set<Long> newsIds);
}
