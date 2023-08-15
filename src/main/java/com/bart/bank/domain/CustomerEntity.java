package com.bart.bank.domain;

import com.bart.bank.customer.CreateCustomer;
import com.bart.bank.customer.UpdateCustomer;
import com.bart.bank.security.BankRole;
import jakarta.persistence.*;
import java.util.Optional;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String firstName;
  String lastName;
  Integer age;
  @Column(unique = true)
  String pesel;

  @Enumerated(EnumType.STRING)
  BankRole role;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  AddressEntity address;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "contact_id")
  ContactEntity contact;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "login_id")
  LoginEntity login;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  AccountEntity account;

  public static CustomerEntity build(CreateCustomer user) {
    return CustomerEntity.builder()
        .firstName(user.firstName())
        .lastName(user.lastName())
        .age(user.age())
        .pesel(user.pesel())
        .role(BankRole.CUSTOMER)
        .address(AddressEntity.build(user))
        .contact(ContactEntity.build(user))
        .login(LoginEntity.build(user))
        .build();
  }

  public void update(UpdateCustomer customer) {
    this.address.country = Optional.ofNullable(customer.country()).orElse(this.address.country);
    this.address.city = Optional.ofNullable(customer.city()).orElse(this.address.city);
    this.address.street = Optional.ofNullable(customer.street()).orElse(this.address.street);
    this.address.houseNumber =
        Optional.ofNullable(customer.houseNumber()).orElse(this.address.houseNumber);
    this.address.flatNumber =
        Optional.ofNullable(customer.flatNumber()).orElse(this.address.flatNumber);
    this.address.postCode = Optional.ofNullable(customer.postCode()).orElse(this.address.postCode);
    this.contact.email = Optional.ofNullable(customer.email()).orElse(this.contact.email);
    this.contact.phoneNumber =
        Optional.ofNullable(customer.phoneNumber()).orElse(this.contact.phoneNumber);
    this.login.password = Optional.ofNullable(customer.password()).orElse(this.login.password);
  }
}
