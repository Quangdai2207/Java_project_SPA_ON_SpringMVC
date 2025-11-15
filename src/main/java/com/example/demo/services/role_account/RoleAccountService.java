package com.example.demo.services.role_account;

import com.example.demo.entities.Account;
import com.example.demo.entities.Role;
import com.example.demo.entities.RoleAccount;
import com.example.demo.repositories.IRoleAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAccountService implements IRoleAccountService {
    @Autowired
    private IRoleAccountRepository roleAccountRepository;

    @Override
    public boolean save(Object input) {
        try {
            if (input instanceof RoleAccount roleAccount) {
                if (roleAccount.getAccount() == null || roleAccount.getRole() == null) {
                    return false;
                }
                roleAccountRepository.save(roleAccount);
            } else if (input instanceof List<?> roleAccounts) {
                for (Object ra : roleAccounts) {
                    if (ra instanceof RoleAccount && (((RoleAccount) ra).getAccount() == null || ((RoleAccount) ra).getRole() == null)) {
                        return false;
                    }
                    roleAccountRepository.saveAll((List<RoleAccount>) roleAccounts);
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public RoleAccount findById(Integer id) {
        return roleAccountRepository.findById(id).orElse(null);
    }
}
