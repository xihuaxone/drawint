package com.drawint.domain.validation;

import com.drawint.domain.validation.validator.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UserNameValidator.class})

public @interface UserName {
    String regex() default "^(?!_)(?!.*_$)[a-zA-Z0-9_]{5,20}$";

    boolean nullAble() default false;

    String message() default "username illegal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
