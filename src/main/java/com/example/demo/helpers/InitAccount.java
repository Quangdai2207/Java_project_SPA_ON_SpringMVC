package com.example.demo.helpers;

import com.example.demo.entities.Account;
import com.example.demo.entities.Role;
import com.example.demo.entities.RoleAccount;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.faculty.FacultyService;
import com.example.demo.services.role.RoleService;
import com.example.demo.services.role_account.RoleAccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InitAccount {
    private static String[] emails = {"superadmin@gmail.com", "admin@gmail.com", "doctor@gmail.com", "patient@gmail.com"};
    private static Object[] faculties = {null, null, 1, null};
    private static String[] dob = {"1988-07-22", "1998-03-10", "1991-10-14", "2000-01-10"};
    private static String[] lnames = {"Tran", "Pham", "Tran", "Sam"};
    private static String[] fnames = {"Dai", "Minh Khang", "Bao Huan", "Gia Cuong"};
    public static boolean initAccounts(
            AccountService accountService,
            RoleService roleService,
            RoleAccountService roleAccountService,
            BCryptPasswordEncoder encoder,
            FacultyService facultyService
    ) {

        List<Role> roles = roleService.findAll();
        List<Account> accounts = new ArrayList<>();
        List<RoleAccount> roleAccounts = new ArrayList<>();
        for (int i = 0; i <= roles.size() -1; i++) {
            if (accountService.findAccountByEmail(emails[i]) != null) {
                continue;
            } else {
                Account account = new Account();
                RoleAccount roleAccount = new RoleAccount();
                account.setPassword(encoder.encode("12345"));
                account.setFaculty(faculties[i] == (Integer) 1 ? facultyService.findById(1) : null);
                account.setDob(LocalDate.parse(dob[i])); // gia tri Date la LocalDate
                account.setGender(Byte.parseByte("0"));
                account.setEmail(emails[i]);
                account.setStatus(true);
                account.setBranchHospital(null);
                account.setFirstName(fnames[i]);
                account.setLastName(lnames[i]);

                roleAccount.setRole(roles.get(i));
                roleAccount.setAccount(account);

                roleAccounts.add(roleAccount);
                accounts.add(account);
            }
            return accountService.save(accounts) && roleAccountService.save(roleAccounts);
        }
        return false;
    }
}
