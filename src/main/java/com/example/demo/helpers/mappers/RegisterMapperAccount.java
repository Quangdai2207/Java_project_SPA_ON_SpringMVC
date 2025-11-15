package com.example.demo.helpers.mappers;

import com.example.demo.DTOs.account.RequestCreateAccount;
import com.example.demo.entities.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterMapperAccount {
    public static Account formMapAccount(RequestCreateAccount body, BCryptPasswordEncoder passwordEncoder) {
        Account account = new Account();
        account.setGender(Byte.parseByte(body.getGender().toString()));
        account.setDob(body.getDob());
        account.setEmail(body.getEmail());
        account.setPassword(passwordEncoder.encode(body.getPassword()));
        account.setStatus(true);
        account.setFaculty(null);
        account.setBranchHospital(null);
        account.setLastName(body.getLastName());
        account.setFirstName(body.getFirstName());
        return account;
    }
}
