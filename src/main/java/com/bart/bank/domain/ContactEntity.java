package com.bart.bank.domain;

import com.bart.bank.customer.CreateCustomer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String email;
  String phoneNumber;

  public static ContactEntity build(CreateCustomer user) {
    return ContactEntity.builder().email(user.email()).phoneNumber(user.phoneNumber()).build();
  }
}
