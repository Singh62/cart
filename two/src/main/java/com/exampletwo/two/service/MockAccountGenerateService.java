package com.exampletwo.two.service;

import com.exampletwo.two.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MockAccountGenerateService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountIdGenerationService accountIdGenerationService;

    public void generateAccounts() {
        Account account1=  Account.builder()
                .id(accountIdGenerationService.newAccountId())
                .balance(new BigDecimal(100))
                .name("Berkay account")
                .build();
        accountRepository.save(account1);

        Account account2=  Account.builder()
                .id(accountIdGenerationService.newAccountId())
                .balance(new BigDecimal(100))
                .name("Test account")
                .build();

        accountRepository.save(account2);
    }
}
