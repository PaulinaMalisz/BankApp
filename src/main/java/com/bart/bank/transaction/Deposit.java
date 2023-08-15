package com.bart.bank.transaction;

import com.bart.bank.domain.Amount;
import com.bart.bank.validation.ValidAmount;

public record Deposit(@ValidAmount Amount amount) {}
