package com.herego.api.utils.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.herego.api.utils.annotations.Validators.UserStateValidator;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserStateValidator.class)
public @interface UserState {
    public String message() default "El estado de usuario no es correspondiente a lo esperado";

    public abstract java.lang.Class<?>[] groups() default {};

    public abstract java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}
