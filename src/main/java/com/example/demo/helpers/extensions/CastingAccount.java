package com.example.demo.helpers.extensions;

import com.example.demo.DTOs.account.RequestCreateAccount;
import com.example.demo.entities.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.UUID;

public class CastingAccount {
    public static Account accountRequestBody(RequestCreateAccount body, BCryptPasswordEncoder encoder) {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setSecurityCode(UUID.randomUUID().toString());
        account.setDob(body.getDob());
        account.setPassword(encoder.encode(body.getPassword()));
        account.setStatus(true);
        account.setGender(Byte.valueOf(body.getGender().toString()));
        account.setFaculty(null);
        account.setFirstName(body.getFirstName());
        account.setLastName(body.getLastName());
        account.setEmail(body.getEmail());
        account.setIsDeleted(false);

        return account;
    }
}
