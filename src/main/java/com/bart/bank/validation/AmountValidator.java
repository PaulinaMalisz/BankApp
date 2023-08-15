package com.bart.bank.validation;

import com.bart.bank.domain.Amount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Currency;

public class AmountValidator implements ConstraintValidator<ValidAmount, Amount> {


  @Override
  public boolean isValid(Amount amount, ConstraintValidatorContext context) {
    if (amount == null) return false;
    var value = amount.getValue();
    if (value == null || value.compareTo(BigDecimal.ZERO) == 0) return false;
    var currency = amount.getCurrency();
    if (currency == null || !currency.equals(Currency.getInstance("PLN"))) return false;
    return true;
  }
}
