package com.bart.bank.transaction;

import com.bart.bank.exception.BankAppException;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("transaction")
public class TransactionController {

  private final TransactionService service;

  @GetMapping("/history")
  public ResponseEntity<List<TransactionSummary>> getHistory() throws BankAppException {
    return ResponseEntity.ok(service.getHistory());
  }

  @PostMapping("/deposit")
  public ResponseEntity<TransactionSummary> deposit(@Valid @RequestBody Deposit deposit)
      throws BankAppException {
    return ResponseEntity.ok(service.deposit(deposit));
  }

  @PostMapping("/withdrawal")
  public ResponseEntity<TransactionSummary> withdrawal(@Valid @RequestBody Withdrawal withdrawal)
      throws BankAppException {
    return ResponseEntity.ok(service.withdrawal(withdrawal));
  }

  @PostMapping("/transfer")
  public ResponseEntity<TransactionSummary> transfer(@Valid @RequestBody Transfer transfer)
      throws BankAppException {
    return ResponseEntity.ok(service.transfer(transfer));
  }
}
