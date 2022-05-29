package com.drawint.domain.validation.validator;

import com.drawint.domain.validation.UserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserName, String> {
    private boolean nullAble;
    private String regex;

    @Override
    public void initialize(UserName constraintAnnotation) {
        nullAble = constraintAnnotation.nullAble();
        regex = constraintAnnotation.regex();

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.isBlank() && !nullAble) {
            return false;
        }
        return Pattern.compile(regex).matcher(s).find();
    }
}
