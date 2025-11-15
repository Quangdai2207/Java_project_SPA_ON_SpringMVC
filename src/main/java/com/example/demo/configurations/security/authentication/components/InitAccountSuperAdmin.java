package com.example.demo.configurations.security.authentication.components;
import com.example.demo.helpers.InitAccount;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.faculty.FacultyService;
import com.example.demo.services.role.RoleService;
import com.example.demo.services.role_account.RoleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitAccountSuperAdmin implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private RoleAccountService roleAccountService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("Initializing Super Admin is running...");
            boolean isSuccess = InitAccount.initAccounts(
                    accountService,
                    roleService,
                    roleAccountService,
                    encoder,
                    facultyService);
            System.out.println(isSuccess ? "Created Success" : "Can not create accounts");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
