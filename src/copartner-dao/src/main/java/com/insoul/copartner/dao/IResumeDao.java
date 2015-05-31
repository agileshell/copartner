package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.Resume;

public interface IResumeDao extends IBaseDao<Resume, Long> {

    List<Resume> getByUserIdAndType(Long userId, Byte type);

    void deleteByUserIdAndType(Long userId, Byte type);
}
