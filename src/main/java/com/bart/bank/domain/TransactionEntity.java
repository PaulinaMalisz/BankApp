package com.bart.bank.domain;

import com.bart.bank.transaction.Deposit;
import com.bart.bank.transaction.Transfer;
import com.bart.bank.transaction.Withdrawal;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "balance")),
    @AttributeOverride(name = "currency", column = @Column(name = "currency"))
  })
  Amount amount;

  @Embedded
  @AttributeOverride(name = "created", column = @Column(name = "created"))
  TimeStamp time;

  String title;
  String transferAccount;
  TransactionType type;

  @ManyToOne
  @JoinColumn(name = "accountId")
  AccountEntity account;

  public static TransactionEntity build(AccountEntity account, Deposit deposit) {
    return TransactionEntity.builder()
        .amount(deposit.amount())
        .time(new TimeStamp())
        .title("WPŁATA NA KONTO")
        .type(TransactionType.DEPOSIT)
        .account(account)
        .build();
  }

  public static TransactionEntity build(AccountEntity account, Withdrawal withdrawal) {
    return TransactionEntity.builder()
        .amount(withdrawal.amount())
        .time(new TimeStamp())
        .title("WYPŁATA Z KONTA")
        .type(TransactionType.WITHDRAWAL)
        .account(account)
        .build();
  }

  public static TransactionEntity build(
      AccountEntity account, Transfer transfer, TransactionType type) {
    return TransactionEntity.builder()
        .amount(transfer.amount())
        .time(new TimeStamp())
        .title(transfer.title())
        .transferAccount(transfer.receiverAccount())
        .type(type)
        .account(account)
        .build();
  }

  public static TransactionEntity build(
      AccountEntity account,
      AccountEntity receiverAccount,
      Transfer transfer,
      TransactionType type) {
    return TransactionEntity.builder()
        .amount(transfer.amount())
        .time(new TimeStamp())
        .title(transfer.title())
        .transferAccount(account.getNumber())
        .type(type)
        .account(receiverAccount)
        .build();
  }
}
