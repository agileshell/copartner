package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.ContentCriteria;
import com.insoul.copartner.domain.Content;

public interface IContentDao extends IBaseDao<Content, Long> {

    List<Content> queryContent(ContentCriteria criteria);

    Long countContent(ContentCriteria criteria);

    long count();

    List<Content> findByContentIds(Set<Long> contentIds);
}
