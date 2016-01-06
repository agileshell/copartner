package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.TutorCriteria;
import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.User;

public interface IUserDao extends IBaseDao<User, Long> {

    User getUserByEmail(String email);

    User getUserByMobile(String mobile);

    List<User> getUserByIds(Set<Long> userIds);

    List<User> query(UserCriteria criteria);
    
    Long count(UserCriteria criteria);

    long count();

    List<User> queryTutor(TutorCriteria criteria);

    Long countTutor(TutorCriteria criteria);

    List<User> findUsers(String keyword);
    
    String getUserName(Long id);
}
