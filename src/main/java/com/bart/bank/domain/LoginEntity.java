package com.bart.bank.domain;

import com.bart.bank.customer.CreateCustomer;
import com.bart.bank.security.BankPasswordEncoder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "login")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String userName;
  String password;

  public static LoginEntity build(CreateCustomer user) {
    return LoginEntity.builder()
        .userName(generateUserName(user))
        .password(BankPasswordEncoder.encode(user.password()))
        .build();
  }

  private static String generateUserName(CreateCustomer user) {
    var shortFirstName = user.firstName().substring(0, 3);
    var shortLastName = user.lastName().substring(0, 3);
    var number = user.pesel().substring(1, 5);
    return shortFirstName + shortLastName + number;
  }
}
