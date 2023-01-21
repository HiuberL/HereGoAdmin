package com.herego.api.utils.annotations.Validators;

import java.util.EnumSet;
import java.util.Iterator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.herego.api.dictionaries.ConfigurationsEnum;
import com.herego.api.dictionaries.ConfigurationsEnum.UserState;
import com.herego.api.utils.annotations.StateUser;
import com.herego.api.utils.annotations.TypeUser;

@SuppressWarnings("all")
public class UserStateValidator implements ConstraintValidator<StateUser, String> {
    @Override
    public boolean isValid(String userType, ConstraintValidatorContext constraintValidatorContext) {
        Iterator<UserState> it = EnumSet.allOf(ConfigurationsEnum.UserState.class).iterator();
        while (it.hasNext()) {
            if (it.next().name().equals(userType))
                return true;
        }
        return false;
    }
}
