package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.ILocationDao;
import com.insoul.copartner.domain.Location;

@Repository
public class LocationDaoImpl extends BaseDaoImpl<Location, Long> implements ILocationDao {

    @Override
    public List<Location> findByParentId(Long parentId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("parentId", parentId);

        return queryByNamedQuery("Location.getByParentId", parameters);
    }

}
