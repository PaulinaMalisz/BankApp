package com.bart.bank.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = Validator.class)
@Documented
public @interface Valid {

  ValidType type();

  String message() default "Invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  enum ValidType {
    EMAIL,
    PESEL,
    POST_CODE,
    PHONE_NUMBER
  }
}
