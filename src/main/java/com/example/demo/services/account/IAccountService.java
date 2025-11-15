package com.example.demo.services.account;

import com.example.demo.entities.*;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    Account findAccountByEmail(String email);
    Account findAccountByFullName(String fname, String lname);
    Account findAccountByPhone(String phoneNumber);
    List<Account> findAccountByRole(Integer roleID);
    List<Account> findAllAccountByStatus(String status);
    List<Account> findAccountsByStatusAndRole(boolean status, Integer roleID);
    Account findAccountByProvince(String province);
    List<Account> findAccountsByFaculty(Integer facultyID);
    List<Account> findAccountsByRoleAndFaculty(Integer roleID, Integer facultyID);
    boolean save(Object input);
    boolean deleteById(String id);
    List<Account> findAll(Sort sort);
    Account findById(String accountID);
    List<Role> getRoles(String email);
    boolean deleteByEmail(String email);
    List<Address> getListAddress(String accountId);
    List<GeneralMedicalInfo> getListGeneralMedicalInfo(String patientId);
    List<MedicalRecord> getListMedicalRecord(String patientId);
    List<Phone> getListPhoneByAccount(String accountId);
}
