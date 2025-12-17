package com.onofre.store.core.validations.ExistsInCollection;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsInCollectionValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsInCollection {
    String message() default "Reference does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String collection();
    String getBy();
}
