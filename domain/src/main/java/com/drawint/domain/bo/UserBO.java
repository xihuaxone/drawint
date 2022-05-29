package com.drawint.domain.bo;

import com.drawint.domain.validation.Gender;
import com.drawint.domain.validation.UserName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserBO {
    @NotNull
    private Long id;

    @UserName
    private String name;

    @Gender
    private String gender;
}
