package com.system.bank.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawRequestModel {
    @JsonProperty(value = "card_number")
    @NotNull(message = "card number is required")
    private String cardNumber;
    @NotNull(message = "cvv is required")
    private String cvv;
    @NotNull(message = "amount is required")
    @Min(value = 0 ,message = "amount cant be negative")
    private Double amount;
}
