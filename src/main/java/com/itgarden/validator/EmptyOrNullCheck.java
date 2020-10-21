package com.itgarden.validator;


import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = EmptyORNullCheckValidator.class)
@Documented

public @interface EmptyOrNullCheck {

    String message() default "Mobile No can't be empty";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
