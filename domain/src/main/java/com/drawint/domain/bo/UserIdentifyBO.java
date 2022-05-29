package com.drawint.domain.bo;

import com.drawint.domain.validation.AccountType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserIdentifyBO {
    @NotNull
    private String account;

    @NotNull
    private String password;

    @AccountType
    private String accountType;
}
