package com.drawint.domain.validation;

import com.drawint.domain.validation.validator.LuminanceRatioValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LuminanceRatioValidator.class})

public @interface LuminanceRatio {
    float min() default 0.0f;

    float max() default 1.0f;

    String message() default "luminance ratio illegal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
