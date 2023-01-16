package com.herego.api.utils.annotations.Validators;

import java.util.EnumSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.herego.api.dictionaries.ConfigurationsEnum;
import com.herego.api.utils.annotations.UserType;

@SuppressWarnings("all")
public class UserTypeValidator implements ConstraintValidator<UserType, String> {
    @Override
    public boolean isValid(String userType, ConstraintValidatorContext constraintValidatorContext) {
        return EnumSet.allOf(ConfigurationsEnum.UserType.class).contains(userType);
    }
}
