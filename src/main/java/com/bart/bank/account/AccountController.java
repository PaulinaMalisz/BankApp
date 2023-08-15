package com.bart.bank.account;

import com.bart.bank.exception.BankAppException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

  private final AccountService service;

  @PostMapping
  public ResponseEntity<String> openAccount(@RequestBody CreateAccount account)
      throws BankAppException {
    return ResponseEntity.ok(service.openAccount(account));
  }

  @PutMapping
  public ResponseEntity<Boolean> closeAccount() throws BankAppException {
    service.closeAccount();
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<AccountSummary> getAccount() throws BankAppException {
    return ResponseEntity.ok(service.getAccountSummary());
  }

  @GetMapping("/confirm")
  public ResponseEntity<List<ConfirmAccount>> getAccountsToConfirm() throws BankAppException {
    return ResponseEntity.ok(service.getAccountsToConfirm());
  }

  @PutMapping("/confirm/{id}")
  public ResponseEntity<Void> confirmAccountOperation(@PathVariable("id") Long id)
      throws BankAppException {
    service.confirmAccountOperation(id);
    return ResponseEntity.ok().build();
  }
}
