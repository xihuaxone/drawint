package com.drawint.domain.bo;

import com.drawint.domain.validation.AccountType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserIdentifyUpdateBO {
    private Long uid;

    @NotNull
    private String account;

    @NotNull
    private String newPassword;

    @AccountType
    private String accountType;

    public UserIdentifyUpdateBO(String account, String newPassword, String accountType) {
        this.account = account;
        this.newPassword = newPassword;
        this.accountType = accountType;
    }
}
