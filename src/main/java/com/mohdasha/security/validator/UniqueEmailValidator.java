package com.mohdasha.security.validator;

import com.mohdasha.security.repository.UserRepository;
import com.mohdasha.security.validator.annotation.UniqueEmail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired private UserRepository userRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotEmpty(value) && userRepository.findByEmail(value) == null;
    }
}
