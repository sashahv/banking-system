package com.olekhv.accountservice.dto;

import com.olekhv.accountservice.enums.AccountCurrencyType;
import com.olekhv.accountservice.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    private LocalDateTime createdAt;
    private AccountType accountType;
    private Long customerId;
    private String iBanNumber;
    private BigDecimal currentBalance;
    private AccountCurrencyType currencyType;
}
