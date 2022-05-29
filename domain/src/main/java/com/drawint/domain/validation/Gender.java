package com.drawint.domain.validation;

import com.drawint.domain.validation.validator.GenderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GenderValidator.class})

public @interface Gender {
    String[] value() default {"b", "g", "u"};

    String message() default "gender illegal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
