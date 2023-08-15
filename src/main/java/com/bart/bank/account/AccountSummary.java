package com.bart.bank.account;

import com.bart.bank.domain.Amount;

public interface AccountSummary {

  String getName();

  String getNumber();

  Amount getAmount();
}
