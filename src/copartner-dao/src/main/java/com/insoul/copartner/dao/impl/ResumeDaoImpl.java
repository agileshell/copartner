package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IResumeDao;
import com.insoul.copartner.domain.Resume;

@Repository
public class ResumeDaoImpl extends BaseDaoImpl<Resume, Long> implements IResumeDao {

    @Override
    public List<Resume> getByUserIdAndType(Long userId, Byte type) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("type", type);

        return queryByNamedQuery("Resume.getByUserIdAndType", parameters);
    }

    @Override
    public void deleteByUserIdAndType(Long userId, Byte type) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("type", type);

        createNamedQuery("Resume.deleteByUserIdAndType", parameters).executeUpdate();
    }
}
