package com.bart.bank.customer;

import com.bart.bank.exception.BankAppException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService service;

  @PostMapping
  public ResponseEntity<String> createCustomer(@Valid @RequestBody CreateCustomer customer)
      throws BankAppException {
    return ResponseEntity.ok(service.createCustomer(customer));
  }

  @PutMapping("/{username}")
  public ResponseEntity<Void> updateCustomer(
      @PathVariable("username") String username, @Valid @RequestBody UpdateCustomer customer)
      throws BankAppException {
    service.updateCustomer(username, customer);
    return ResponseEntity.ok().build();
  }
}
