package com.drawint.domain.bo;

import lombok.Data;


@Data
public class LoginBO {
    private String account;

    private String password;

    private String accountType;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "account='" + account + '\'' +
                ", password='" + "***(length=" + password.length() + ")" + '\'' +
                '}';
    }
}
