package com.bart.bank.security;

import com.bart.bank.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {

  private final CustomerRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user =
        repository
            .findByLoginUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not found"));

    return User.builder()
        .username(user.getLogin().getUserName())
        .password(user.getLogin().getPassword())
        .disabled(false)
        .accountExpired(false)
        .credentialsExpired(false)
        .accountLocked(false)
        .authorities(user.getRole().name())
        .build();
  }
}
