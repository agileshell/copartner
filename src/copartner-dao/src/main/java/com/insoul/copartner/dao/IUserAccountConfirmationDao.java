package com.insoul.copartner.dao;

import com.insoul.copartner.domain.UserAccountConfirmation;
import com.insoul.copartner.domain.UserAccountConfirmationId;

public interface IUserAccountConfirmationDao extends IBaseDao<UserAccountConfirmation, UserAccountConfirmationId> {

    UserAccountConfirmation getUserAccountConfirmation(String accountType, String account, String code);
}
