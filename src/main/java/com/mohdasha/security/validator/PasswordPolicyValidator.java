package com.mohdasha.security.validator;

import com.mohdasha.security.validator.annotation.PasswordPolicy;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyValidator implements ConstraintValidator<PasswordPolicy, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        List<Rule> listRules = new ArrayList<>();

        listRules.add(new LengthRule(7, 128));

        CharacterCharacteristicsRule characteristicsRule = new CharacterCharacteristicsRule(3,
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1));

        listRules.add(characteristicsRule);

        PasswordValidator passwordValidator = new PasswordValidator(listRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);
        return ruleResult.isValid();
    }
}
