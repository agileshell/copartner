package com.insoul.copartner.dao;

import com.insoul.copartner.domain.UserPasswordReset;

public interface IUserPasswordResetDao extends IBaseDao<UserPasswordReset, Long> {

    UserPasswordReset getUserPasswordReset(Long userId, String code);
}
