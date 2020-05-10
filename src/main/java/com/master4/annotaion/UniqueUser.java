package com.master4.annotaion;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueUserValidation.class })
public @interface UniqueUser {
    String message() default "Email already existed";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
