package com.bart.bank.transaction;

import com.bart.bank.account.AccountService;
import com.bart.bank.domain.AccountEntity;
import com.bart.bank.domain.TransactionEntity;
import com.bart.bank.domain.TransactionType;
import com.bart.bank.exception.BankAppException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository repository;
  private final AccountService accountService;

  public List<TransactionSummary> getHistory() throws BankAppException {
    var account = accountService.findCustomerAccount();
    return repository.findByAccountId(account.getId());
  }

  @Transactional
  public TransactionSummary deposit(Deposit deposit) throws BankAppException {
    var account = accountService.findCustomerAccount();
    if (account.isInactive()) throw BankAppException.ACCOUNT_INACTIVE;
    var transaction = TransactionEntity.build(account, deposit);
    account.depositMoney(transaction.getAmount().getValue());
    transaction = repository.save(transaction);
    accountService.save(account);
    return repository.getById(transaction.getId(), TransactionSummary.class);
  }

  @Transactional
  public TransactionSummary withdrawal(Withdrawal withdrawal) throws BankAppException {
    var account = accountService.findCustomerAccount();
    if (account.isInactive()) throw BankAppException.ACCOUNT_INACTIVE;
    var transaction = TransactionEntity.build(account, withdrawal);
    account.withdrawMoney(transaction.getAmount().getValue());
    transaction = repository.save(transaction);
    accountService.save(account);
    return repository.getById(transaction.getId(), TransactionSummary.class);
  }

  @Transactional
  public TransactionSummary transfer(Transfer transfer) throws BankAppException {
    var account = accountService.findCustomerAccount();
    if (account.isInactive()) throw BankAppException.ACCOUNT_INACTIVE;
    var receiverAccount = accountService.findAccountByNumber(transfer.receiverAccount());
    if (receiverAccount.isEmpty()) return transferExternal(transfer, account);
    else return transferInternal(transfer, account, receiverAccount.get());
  }

  private TransactionSummary transferInternal(
      Transfer transfer, AccountEntity account, AccountEntity receiverAccount)
      throws BankAppException {
    var transaction = TransactionEntity.build(account, transfer, TransactionType.INTERNAL_OUT);
    var receiverTransaction =
        TransactionEntity.build(account, receiverAccount, transfer, TransactionType.INTERNAL_IN);
    account.withdrawMoney(transaction.getAmount().getValue());
    receiverAccount.depositMoney(transaction.getAmount().getValue());
    transaction = repository.save(transaction);
    repository.save(receiverTransaction);
    accountService.save(account);
    accountService.save(receiverAccount);
    return repository.getById(transaction.getId(), TransactionSummary.class);
  }

  private TransactionSummary transferExternal(Transfer transfer, AccountEntity account)
      throws BankAppException {
    var transaction = TransactionEntity.build(account, transfer, TransactionType.EXTERNAL_OUT);
    account.withdrawMoney(transaction.getAmount().getValue());
    transaction = repository.save(transaction);
    accountService.save(account);
    return repository.getById(transaction.getId(), TransactionSummary.class);
  }
}
