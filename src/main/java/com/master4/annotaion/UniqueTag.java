package com.master4.annotaion;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)

@Constraint(validatedBy = {UniqueTagValidation.class })
public @interface UniqueTag {
    String message() default "title already existed";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
