package com.bart.bank.transaction;

import com.bart.bank.domain.Amount;
import com.bart.bank.domain.TransactionType;
import org.springframework.beans.factory.annotation.Value;

public interface TransactionSummary {
  Amount getAmount();

  @Value("#{target.time.time}")
  String getTime();

  TransactionType getType();

  String getTransferAccount();

  String getTitle();
}
