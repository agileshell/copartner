package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserAccountConfirmationDao;
import com.insoul.copartner.domain.UserAccountConfirmation;
import com.insoul.copartner.domain.UserAccountConfirmationId;

@Repository
public class UserAccountConfirmationDaoImpl extends BaseDaoImpl<UserAccountConfirmation, UserAccountConfirmationId>
        implements IUserAccountConfirmationDao {

    @Override
    public UserAccountConfirmation getUserAccountConfirmation(String accountType, String account, String code) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("accountType", accountType);
        parameters.put("account", account);
        parameters.put("code", code);

        return queryOneByNamedQuery("UserAccountConfirmation.findUserAccountConfirmation", parameters);
    }

}
