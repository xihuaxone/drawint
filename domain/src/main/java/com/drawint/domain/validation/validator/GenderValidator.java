package com.drawint.domain.validation.validator;

import com.drawint.domain.validation.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {
    private String[] value;

    @Override
    public void initialize(Gender constraintAnnotation) {
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
