package com.iumutdikbasan.weatherapp.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueUsernameValidator.class})
public @interface UniqueUsername {

    String message() default "Username daha önce kullanılmış";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}