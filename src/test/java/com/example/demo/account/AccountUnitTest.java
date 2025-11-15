package com.example.demo.account;

import com.example.demo.entities.Account;
import com.example.demo.services.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional(readOnly = true)
public class AccountUnitTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void testAccountNotNull() {
        String email = "admin@gmail.com";
        Account acc = accountService.findAccountByEmail(email); // giả sử service có method này
        assertEquals(email, acc.getEmail());
    }
}
