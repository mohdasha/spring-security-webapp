package com.mohdasha.security.validator.annotation;

import com.mohdasha.security.validator.PasswordConfirmedValidator;
import com.mohdasha.security.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmedValidator.class)
public @interface PasswordConfirmed {
    String message() default "Password and Confirm Password do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
