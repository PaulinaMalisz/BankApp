package com.bart.bank.domain;

import com.bart.bank.account.CreateAccount;
import com.bart.bank.exception.BankAppException;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;
  String number;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "balance")),
    @AttributeOverride(name = "currency", column = @Column(name = "currency"))
  })
  Amount amount;

  @Enumerated(value = EnumType.STRING)
  Status status;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  List<TransactionEntity> transactions;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  CustomerEntity customer;

  public static AccountEntity build(CustomerEntity customer, CreateAccount account) {
    return AccountEntity.builder()
        .name(Optional.ofNullable(account.name()).orElse("Konto bieżące"))
        .number(generateAccountNumber())
        .amount(Amount.ZERO)
        .status(Status.REQUESTED_TO_OPEN)
        .customer(customer)
        .build();
  }

  private static String generateAccountNumber() {
    var random = new Random();
    return random.ints(15, 0, 10).mapToObj(Integer::toString).reduce("", (s1, s2) -> s1 + s2);
  }

  public void closeAccount() throws BankAppException {
    if (Status.ACTIVE.equals(status)) this.status = Status.CLOSED;
    else throw BankAppException.ACCOUNT_INACTIVE_CLOSURE;
  }

  public void updateStatus() throws BankAppException {
    switch (status) {
      case REQUESTED_TO_OPEN -> status = Status.ACTIVE;
      case REQUESTED_TO_CLOSE -> status = Status.CLOSED;
      default -> throw BankAppException.ACCOUNT_CONFIRMED;
    }
  }

  public boolean isInactive() {
    return !Status.ACTIVE.equals(status);
  }

  public void depositMoney(BigDecimal value) {
    amount.value = amount.value.add(value);
  }

  public void withdrawMoney(BigDecimal value) throws BankAppException {
    if (amount.value.compareTo(value) < 0) throw BankAppException.BALANCE_NOT_ENOUGH;
    amount.value = amount.value.subtract(value);
  }
}
