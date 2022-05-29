package com.drawint.domain.validation.validator;

import com.drawint.domain.validation.LuminanceRatio;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LuminanceRatioValidator implements ConstraintValidator<LuminanceRatio, Float> {
    private float min;
    private float max;

    @Override
    public void initialize(LuminanceRatio constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Float v, ConstraintValidatorContext constraintValidatorContext) {
        return (v <= max) && (v >= min);
    }
}
