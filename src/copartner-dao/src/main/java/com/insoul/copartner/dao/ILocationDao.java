package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.Location;

public interface ILocationDao extends IBaseDao<Location, Long> {

    List<Location> findByParentId(Long parentId);

}
