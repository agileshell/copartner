package com.insoul.copartner.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.insoul.copartner.constant.UserStatus;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.security.model.UserDetailsImpl;
import com.insoul.copartner.util.ValidationUtil;

public class UserDetailsServiceImpl implements UserDetailsService {

    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = null;
        if (ValidationUtil.isEmail(username)) {
            user = userDao.getUserByEmail(username);
        } else if (ValidationUtil.isMobilePhoneNumber(username)) {
            user = userDao.getUserByMobile(username);
        }

        if (null == user || !UserStatus.ACTIVE.getValue().equals(user.getStatus())) {
            throw new UsernameNotFoundException("error_no_user");
        }

        UserDetailsImpl details = new UserDetailsImpl(username, user.getPassword(), true, true, true, true,
                getAuthorities(0));
        details.setUserId(user.getId());
        details.setName(username);
        details.setSalt(user.getSalt());

        return details;
    }

    public Collection<GrantedAuthority> getAuthorities(Integer userRoleId) {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(new SimpleGrantedAuthority(String.valueOf(userRoleId)));

        return result;
    }

}
