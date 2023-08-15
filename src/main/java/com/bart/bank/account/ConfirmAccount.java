package com.bart.bank.account;

import com.bart.bank.domain.Status;
import org.springframework.beans.factory.annotation.Value;

public interface ConfirmAccount {

  Long getId();

  String getNumber();

  Status getStatus();

  @Value("#{target.customer.firstName + ' ' + target.customer.lastName}")
  String getCustomer();
}
