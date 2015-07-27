package com.insoul.copartner.dao;

import com.insoul.copartner.domain.Admin;

public interface AdminDAO extends IBaseDao<Admin, Long> {
	Admin queryAdmin(String loginName);
}