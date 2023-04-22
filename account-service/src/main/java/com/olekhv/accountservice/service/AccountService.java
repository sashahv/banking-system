package com.olekhv.accountservice.service;

import com.olekhv.accountservice.dto.AccountRequest;
import com.olekhv.accountservice.entity.Account;
import com.olekhv.accountservice.enums.AccountCurrencyType;
import com.olekhv.accountservice.enums.AccountType;
import com.olekhv.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void createAccount(AccountRequest accountRequest){
        mapRequestToAccount(accountRequest);
    }

    private Account mapRequestToAccount(AccountRequest accountRequest) {
        return Account.builder()
                .createdAt(LocalDate.now())
                .accountType(AccountType.ACTIVE)
                .currencyType(AccountCurrencyType.USD)
                .currentBalance(BigDecimal.valueOf(0))
                .customerId(null) //todo
                .build();
    }
}
