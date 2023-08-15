package com.bart.bank.customer;

import static com.bart.bank.security.BankRole.ADMIN_ROLE;

import com.bart.bank.domain.CustomerEntity;
import com.bart.bank.exception.BankAppException;
import com.bart.bank.security.CustomerInfoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;

  @Secured(ADMIN_ROLE)
  public String createCustomer(CreateCustomer user) throws BankAppException {
    var result = repository.save(CustomerEntity.build(user));
    return result.getLogin().getUserName();
  }

  @Secured(ADMIN_ROLE)
  public void updateCustomer(String username, UpdateCustomer user) throws BankAppException {
    var result =
        repository.findByLoginUserName(username).orElseThrow(() -> BankAppException.USER_NOT_FOUND);
    result.update(user);
    repository.save(result);
  }

  public void save(CustomerEntity customer) {
    repository.save(customer);
  }

  public CustomerEntity getAuthenticatedCustomer() throws BankAppException {
    return repository
        .findByLoginUserName(CustomerInfoProvider.userName())
        .orElseThrow(() -> BankAppException.AUTHORIZED_USER_NOT_FOUND);
  }
}
