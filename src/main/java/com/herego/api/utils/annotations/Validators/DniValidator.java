package com.herego.api.utils.annotations.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.herego.api.utils.annotations.DNI;

public class DniValidator implements ConstraintValidator<DNI, String> {
    @Override
    public boolean isValid(String dni, ConstraintValidatorContext constraintValidatorContext) {

        return false;
    }
}
