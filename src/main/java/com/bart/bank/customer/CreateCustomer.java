package com.bart.bank.customer;

import com.bart.bank.validation.ValidString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCustomer(
    @NotNull @NotBlank String firstName,
    @NotNull @NotBlank String lastName,
    @NotNull Integer age,
    @NotNull @NotBlank @ValidString(type = ValidString.ValidType.PESEL) String pesel,
    @NotNull @NotBlank String country,
    @NotNull @NotBlank String city,
    @NotNull @NotBlank String street,
    @NotNull Integer houseNumber,
    Integer flatNumber,
    @NotNull @NotBlank @ValidString(type = ValidString.ValidType.POST_CODE) String postCode,
    @NotNull @NotBlank @ValidString(type = ValidString.ValidType.EMAIL) String email,
    @NotNull @NotBlank @ValidString(type = ValidString.ValidType.PHONE_NUMBER) String phoneNumber,
    @NotNull @NotBlank String password) {}
