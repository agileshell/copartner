package com.insoul.copartner.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class PasswordRestRequest implements Serializable {

    private static final long serialVersionUID = 4700931992684306191L;

    @NotBlank
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    private String code;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
