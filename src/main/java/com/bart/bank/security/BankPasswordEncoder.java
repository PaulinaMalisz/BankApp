package com.bart.bank.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BankPasswordEncoder {

  public static final PasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String encode(String password) {
    return encoder.encode(password);
  }
}
