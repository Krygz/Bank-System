package com.system.bank.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseModel {
    @JsonProperty(value = "card_number")
    private String cardNumber;
    private String cvv;
    private Double balance;
}

