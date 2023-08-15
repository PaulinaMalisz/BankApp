package com.bart.bank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BankAppException extends Exception {

  public static final BankAppException ACCOUNT_NOT_FOUND =
      new BankAppException("Account not found", HttpStatus.NOT_FOUND);
  public static final BankAppException ACCOUNT_ALREADY_EXISTS =
      new BankAppException("Account already exists for this customer", HttpStatus.CONFLICT);
  public static final BankAppException ACCOUNT_INACTIVE =
      new BankAppException("Account is inactive", HttpStatus.CONFLICT);
  public static final BankAppException ACCOUNT_INACTIVE_CLOSURE =
      new BankAppException("Account is inactive and can not be closed", HttpStatus.CONFLICT);
  public static final BankAppException ACCOUNT_CONFIRMED =
      new BankAppException("Account is already in confirmed status", HttpStatus.CONFLICT);
  public static final BankAppException BALANCE_NOT_ENOUGH =
      new BankAppException(
          "Account balance is not high enough to execute the transaction", HttpStatus.CONFLICT);
  public static final BankAppException USER_NOT_FOUND =
      new BankAppException("User not found", HttpStatus.NOT_FOUND);
  public static final BankAppException AUTHORIZED_USER_NOT_FOUND =
      new BankAppException("Authorized user not found", HttpStatus.NOT_FOUND);

  @Getter private final HttpStatus status;

  public BankAppException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
