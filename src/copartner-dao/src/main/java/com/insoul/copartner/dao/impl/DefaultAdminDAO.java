package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.AdminDAO;
import com.insoul.copartner.domain.Admin;

@Repository
public class DefaultAdminDAO extends BaseDaoImpl<Admin, Long> implements AdminDAO {

	@Override
	public Admin queryAdmin(String loginName) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("loginName", loginName);
		return (Admin) createNamedQuery("Admin.queryAdmin", args).getSingleResult();
	}
}