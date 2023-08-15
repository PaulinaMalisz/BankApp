package com.bart.bank.customer;

import com.bart.bank.validation.ValidString;

public record UpdateCustomer(
    String country,
    String city,
    String street,
    Integer houseNumber,
    Integer flatNumber,
    @ValidString(type = ValidString.ValidType.POST_CODE) String postCode,
    @ValidString(type = ValidString.ValidType.EMAIL) String email,
    @ValidString(type = ValidString.ValidType.PHONE_NUMBER) String phoneNumber,
    String password) {}
