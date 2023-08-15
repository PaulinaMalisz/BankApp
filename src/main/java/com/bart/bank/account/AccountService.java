package com.bart.bank.account;

import static com.bart.bank.security.BankRole.ADMIN_ROLE;

import com.bart.bank.customer.CustomerService;
import com.bart.bank.domain.AccountEntity;
import com.bart.bank.domain.Status;
import com.bart.bank.exception.BankAppException;
import com.bart.bank.security.CustomerInfoProvider;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository repository;
  private final CustomerService customerService;

  public String openAccount(CreateAccount account) throws BankAppException {
    var customer = customerService.getAuthenticatedCustomer();
    if (customer.getAccount() != null) throw BankAppException.ACCOUNT_ALREADY_EXISTS;
    var result = repository.save(AccountEntity.build(customer, account));
    customer.setAccount(result);
    customerService.save(customer);
    return result.getNumber();
  }

  public void closeAccount() throws BankAppException {
    var account = findCustomerAccount();
    account.closeAccount();
    repository.save(account);
  }

  public AccountSummary getAccountSummary() throws BankAppException {
    var account = findCustomerAccount();
    return repository.getById(account.getId(), AccountSummary.class);
  }

  @Secured(ADMIN_ROLE)
  public List<ConfirmAccount> getAccountsToConfirm() throws BankAppException {
    return repository.findByStatusIn(List.of(Status.REQUESTED_TO_OPEN, Status.REQUESTED_TO_CLOSE));
  }

  @Secured(ADMIN_ROLE)
  public void confirmAccountOperation(Long id) throws BankAppException {
    var account = findAccount(id);
    account.updateStatus();
    repository.save(account);
  }

  public AccountEntity findCustomerAccount() throws BankAppException {
    return repository
        .findByCustomerLoginUserName(CustomerInfoProvider.userName())
        .orElseThrow(() -> BankAppException.ACCOUNT_NOT_FOUND);
  }

  public Optional<AccountEntity> findAccountByNumber(String number) {
    return repository.findByNumber(number);
  }

  public AccountEntity findAccount(Long accountId) throws BankAppException {
    return repository.findById(accountId).orElseThrow(() -> BankAppException.ACCOUNT_NOT_FOUND);
  }

  public void save(AccountEntity account) {
    repository.save(account);
  }
}
