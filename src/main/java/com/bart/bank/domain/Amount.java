package com.bart.bank.domain;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Amount {

  public static Amount ZERO = new Amount(BigDecimal.ZERO, Currency.getInstance("PLN"));

  BigDecimal value;
  Currency currency;
}
