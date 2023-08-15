package com.bart.bank.domain;

import com.bart.bank.customer.CreateCustomer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String country;
  String city;
  String street;
  Integer houseNumber;
  Integer flatNumber;
  String postCode;

  public static AddressEntity build(CreateCustomer user) {
    return AddressEntity.builder()
        .country(user.country())
        .city(user.city())
        .street(user.street())
        .houseNumber(user.houseNumber())
        .flatNumber(user.flatNumber())
        .postCode(user.postCode())
        .build();
  }
}
