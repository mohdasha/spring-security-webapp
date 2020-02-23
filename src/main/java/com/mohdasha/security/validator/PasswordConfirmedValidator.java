package com.mohdasha.security.validator;

import com.mohdasha.security.model.UserRegistrationDto;
import com.mohdasha.security.validator.annotation.PasswordConfirmed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserRegistrationDto dto = (UserRegistrationDto) value;

        return dto.getPassword().equals(dto.getConfirmPassword());
    }
}
