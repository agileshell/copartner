package com.insoul.ti.shiro;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insoul.copartner.dao.AdminDAO;
import com.insoul.copartner.domain.Admin;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.copartner.utils.Permission;
import com.insoul.ti.utils.Constants;

public class WebAuthRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(WebAuthRealm.class);
    
    @Resource
    protected AdminDAO adminDAO;
    
    @Override
    protected void onInit() {
        super.onInit();
        if (getCacheManager() == null) {
            setCacheManager(new MemoryConstrainedCacheManager());
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (admin.hasPermission(Permission.SuperAdmin)) {
            info.addRole("sadmin");
        }
        if (admin.hasPermission(Permission.Admin)) {
            info.addRole("admin");
        }
        if (admin.hasPermission(Permission.User)) {
            info.addRole("buser");
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        AuthenticationInfo authenticationInfo = null;
        Admin admin = null;
        try {
            admin = adminDAO.queryAdmin(token.getUsername());
        } catch (Throwable ex) {
            log.error(ex.getMessage());
            throw new AuthenticationException(ex);
        }
        if (StringUtils.equals(PasswordUtil.encodePassword(new String(token.getPassword()), Constants.DEFAULT_ADMIN_SALT), admin.getPassword())) {
            authenticationInfo = new SimpleAuthenticationInfo(admin, token.getPassword(), token.getUsername());
        }
        return authenticationInfo;
    }
}