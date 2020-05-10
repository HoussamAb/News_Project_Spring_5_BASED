package com.master4.annotaion;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueArticleValidation.class })
public @interface UniqueArticle {
    String message() default "Artcile already existed";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
