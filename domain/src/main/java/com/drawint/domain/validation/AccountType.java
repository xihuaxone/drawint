package com.drawint.domain.validation;

import com.drawint.domain.validation.validator.AccountTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AccountTypeValidator.class})

public @interface AccountType {
    String[] value() default {"DRAW_INT"};

    String message() default "account type illegal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
