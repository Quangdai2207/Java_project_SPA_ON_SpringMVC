package com.example.demo.services.role_account;

import com.example.demo.entities.Account;
import com.example.demo.entities.Role;
import com.example.demo.entities.RoleAccount;

public interface IRoleAccountService {
    boolean save(Object input);
    RoleAccount findById(Integer id);
}
