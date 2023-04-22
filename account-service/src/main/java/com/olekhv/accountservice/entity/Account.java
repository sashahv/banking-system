package com.olekhv.accountservice.entity;

import com.olekhv.accountservice.enums.AccountCurrencyType;
import com.olekhv.accountservice.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "iban_number", length = 34, nullable = false)
    private String iBanNumber;

    @Column(name = "current_balance", nullable = false)
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_type", nullable = false)
    private AccountCurrencyType currencyType;
}
