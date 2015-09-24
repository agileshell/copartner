package com.insoul.ti.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insoul.copartner.domain.Admin;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.shiro.Permission;
import com.insoul.ti.utils.Constants;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:23
 */
@Controller
@RequestMapping("/async")
@Permission("authc")
public class AsyncController extends WebBase {

    @RequestMapping("/password/change")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void changePasswordAction(@RequestParam(value = "old_password") String old_password, @RequestParam(value = "new_password") String new_password) {
        Admin admin = adminDAO.get(getAdminId());
        if (!StringUtils.equals(PasswordUtil.encodePassword(old_password, Constants.DEFAULT_ADMIN_PASSWORD_SALT), admin.getPassword())) {
            returnJson(false, "200", "原密码不正确!");
            return;
        }
        admin.setPassword(PasswordUtil.encodePassword(new_password, Constants.DEFAULT_ADMIN_PASSWORD_SALT));
        admin.setUpdated(new Date());
        adminDAO.update(admin);
        returnJson(true, "200", "密码修改成功!");
    }
}