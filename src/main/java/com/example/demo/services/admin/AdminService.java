package com.example.demo.services.admin;

import com.example.demo.entities.Account;
import com.example.demo.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminService implements IAdminService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Account> getAdmins(Integer roleID) {
        return this.accountRepository.findAccountByRole(roleID);
    }

    @Override
    public List<Account> getDoctors(Integer roleID) {
        return this.accountRepository.findAccountByRole(roleID);
    }

    @Override
    public List<Account> getPatients(Integer roleID) {
        return this.accountRepository.findAccountByRole(roleID);
    }

    @Override
    public List<Account> getDoctorsByFaculty(Integer roleID, Integer facultyID) {
        return this.accountRepository.findAccountsByRoleAndFaculty(roleID, facultyID);
    }

    @Override
    public List<Account> getPatientsByFaculty(Integer roleID, Integer facultyID) {
        return this.accountRepository.findAccountsByRoleAndFaculty(roleID, facultyID);
    }
}
