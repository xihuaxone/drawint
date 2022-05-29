package com.drawint.domain.validation.validator;

import com.drawint.domain.validation.AccountType;
import com.drawint.domain.validation.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountTypeValidator implements ConstraintValidator<AccountType, String> {
    private String[] value;

    @Override
    public void initialize(AccountType constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (int i = 0; i < value.length; i++) {
            if (s.equals(value[i]))
                return true;
        }
        return false;
    }
}
