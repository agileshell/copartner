package com.insoul.copartner.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringPattern;

public class UserAddRequest implements Serializable {

    private static final long serialVersionUID = -8218298663013977302L;

    private String name;

    @NotBlank
    @StringPattern(regexp = "^(1[0-9]{10})|([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)"
            + "|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
    private String account;

    @NotBlank
    private String password;

    private String code;
    
    private Long professionId = 1L;// 1:学术型 2:实业型

    public Long getProfessionId() {
        if (professionId == null || professionId <= 0L) {
            return 1L;
        }
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
