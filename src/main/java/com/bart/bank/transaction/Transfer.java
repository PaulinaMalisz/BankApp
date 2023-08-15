package com.bart.bank.transaction;

import com.bart.bank.domain.Amount;
import com.bart.bank.validation.ValidAmount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Transfer(
    @ValidAmount Amount amount,
    @NotNull @NotBlank String title,
    @NotNull @NotBlank String receiverAccount) {}
