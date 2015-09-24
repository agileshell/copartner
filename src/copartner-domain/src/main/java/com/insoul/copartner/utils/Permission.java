package com.insoul.copartner.utils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月22日 下午3:11:22
 */
public enum Permission {

    SuperAdmin(2, "超级管理员"),
    
    Admin(1, "管理员"),
    
    User(0, "一般用户")
    ;
    
    public int code;
    public String description;
    
    public static Permission parse(int code) {
        for (Permission p : values()) {
            if (code == p.code) {
                return p;
            }
        }
        return User;
    }
    
    private Permission(int code, String description) {
        this.code = code;
        this.description = description;
    }
}