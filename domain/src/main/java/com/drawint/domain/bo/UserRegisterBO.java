package com.drawint.domain.bo;

import com.drawint.domain.validation.AccountType;
import com.drawint.domain.validation.Gender;
import com.drawint.domain.validation.UserName;
import lombok.Data;

@Data
public class UserRegisterBO {
    @UserName
    private String name;

    @Gender
    private String gender;

    @AccountType
    private String accountType;

    private String account;

    private String password;
}
