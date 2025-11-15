package com.example.demo.services.account;

import com.example.demo.entities.*;
import com.example.demo.repositories.IAccountRepository;
import com.example.demo.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("AccountService")
public class AccountService implements IAccountService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public Account findAccountByFullName(String fName, String lName) {
        return this.accountRepository.findAccountByFullName(fName, lName);
    }

    @Override
    public Account findAccountByPhone(String phoneNumber) {
        return this.accountRepository.findAccountByPhone(phoneNumber);
    }

    @Override
    public List<Account> findAccountByRole(Integer roleID) {
        return this.accountRepository.findAccountByRole(roleID);
    }

    @Override
    public List<Account> findAllAccountByStatus(String status) {
        return this.accountRepository.findAllAccountByStatus(status);
    }

    @Override
    public List<Account> findAccountsByStatusAndRole(boolean status, Integer roleID) {
        return this.accountRepository.findAccountsByStatusAndRole(status, roleID);
    }

    @Override
    public Account findAccountByProvince(String province) {
        return this.accountRepository.findAccountByAddress(province);
    }

    @Override
    public List<Account> findAccountsByFaculty(Integer facultyID) {
        return this.accountRepository.findAccountsByFaculty(facultyID);
    }

    @Override
    public List<Account> findAccountsByRoleAndFaculty(Integer roleID, Integer facultyID) {
        return this.accountRepository.findAccountsByRoleAndFaculty(roleID, facultyID);
    }

    @Override
    public boolean save(Object input) {
        try {
            if (input instanceof Account account) {
                this.accountRepository.save(account);
            } else if (input instanceof List<?> accounts) {
                this.accountRepository.saveAll((List<Account>) accounts);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(String id) {
        try {
            this.accountRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByEmail(String email) {
        try {
            this.accountRepository.deleteByEmail(email);
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Address> getListAddress(String accountId) {
        return accountRepository.findAddress(accountId);
    }

    @Override
    public List<GeneralMedicalInfo> getListGeneralMedicalInfo(String patientId) {
        return accountRepository.getGeneralMedicalInfo(patientId);
    }

    @Override
    public List<MedicalRecord> getListMedicalRecord(String patientId) {
        return accountRepository.getMedicalRecord(patientId);
    }

    @Override
    public List<Phone> getListPhoneByAccount(String accountId) {
        return accountRepository.getListPhoneByAccount(accountId);
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return this.accountRepository.findAll();
    }

    public Account findById(String accountID) {
        return this.accountRepository.findById(accountID).orElse(null);
    }

    @Override
    public List<Role> getRoles(String email) {
        return this.accountRepository.getRoles(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = this.findAccountByEmail(email);
        System.out.println(">>> loadUserByUsername() called for email: " + email);
        if (account == null) {
            throw new UsernameNotFoundException("Email khong ton tai");
        } else {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Role role : roleService.getRolesByAccountId(account.getId())) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new User(email, account.getPassword(), authorities);
        }
    }
}
