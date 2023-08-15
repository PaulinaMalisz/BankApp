package com.bart.bank.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator implements ConstraintValidator<ValidString, String> {

  private static final String EMAIL_PATTERN =
      "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
  private static final String PESEL_PATTERN = "[0-9]{11}";
  private static final String POST_CODE_PATTERN = "[0-9]{5}";
  private static final String PHONE_NUMBER_PATTERN = "[0-9]{9}";

  private String type;

  @Override
  public void initialize(ValidString constraintAnnotation) {
    type =
        switch (constraintAnnotation.type()) {
          case EMAIL -> EMAIL_PATTERN;
          case PESEL -> PESEL_PATTERN;
          case POST_CODE -> POST_CODE_PATTERN;
          case PHONE_NUMBER -> PHONE_NUMBER_PATTERN;
        };
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) return true;
    Pattern pattern = Pattern.compile(type);
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }
}
