package com.bart.bank.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomerInfoProvider {

  public static String userName() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
