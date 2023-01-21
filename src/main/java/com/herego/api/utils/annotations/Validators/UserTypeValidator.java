package com.herego.api.utils.annotations.Validators;

import java.util.EnumSet;
import java.util.Iterator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.herego.api.dictionaries.ConfigurationsEnum;
import com.herego.api.dictionaries.ConfigurationsEnum.UserType;
import com.herego.api.utils.annotations.TypeUser;

@SuppressWarnings("all")
public class UserTypeValidator implements ConstraintValidator<TypeUser, String> {
    @Override
    public boolean isValid(String userType, ConstraintValidatorContext constraintValidatorContext) {
        Iterator<UserType> it = EnumSet.allOf(ConfigurationsEnum.UserType.class).iterator();
        while (it.hasNext()) {
            if (it.next().name().equals(userType))
                return true;
        }
        return false;
    }
}
