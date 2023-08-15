package com.bart.bank.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {BankAppException.class})
  protected ResponseEntity<Object> handleConflict(BankAppException ex) {
    return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), ex.getStatus());
  }
}
