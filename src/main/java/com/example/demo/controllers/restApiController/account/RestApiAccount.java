package com.example.demo.controllers.restApiController.account;

import com.example.demo.DTOs.account.RequestCreateAccount;
import com.example.demo.entities.*;
import com.example.demo.helpers.mappers.RegisterMapperAccount;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.address.AddressService;
import com.example.demo.services.phone.PhoneService;
import com.example.demo.services.role.RoleService;
import com.example.demo.services.role_account.RoleAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account/api/v1")
public class RestApiAccount {
    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleAccountService roleAccountService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/list", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getAll() {
        try {
            List<Account> accounts = accountService.findAll(null);
            if (accounts.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(
            @Valid @RequestBody RequestCreateAccount formBody,
            BindingResult result
    ) {
        Map<String, Object> res = new HashMap<>();

        // 1. Kiểm tra format
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            res.put("success", false);
            res.put("errors", errors);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 2. Kiểm tra mật khẩu xác nhận
        if (!formBody.getPassword().equals(formBody.getCfpassword())) {
            res.put("success", false);
            res.put("errors", Map.of("cfpassword", "Xác nhận mật khẩu không khớp."));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 3. Kiểm tra email trùng
        if (accountService.findAccountByEmail(formBody.getEmail()) != null) {
            res.put("success", false);
            res.put("errors", Map.of("email", "Email này đã được đăng ký."));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 5. Kiểm tra tên người dùng
        if (accountService.findAccountByFullName(formBody.getFirstName(), formBody.getLastName()) != null) {
            res.put("success", false);
            res.put("errors", Map.of("fullName", "Tên người dùng đã tồn tại."));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 6. Tạo tài khoản
        Account account = RegisterMapperAccount.formMapAccount(formBody, encoder);
        boolean isSavedAccount = accountService.save(account);
        if (isSavedAccount) {
            RoleAccount roleAccount = new RoleAccount();
            roleAccount.setRole(roleService.findById(4));
            roleAccount.setAccount(account);
            roleAccountService.save(roleAccount);
        } else {
            res.put("success", false);
            res.put("errors", Map.of("account", "Tạo tài khoản không thành công."));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 7. Thành công
        res.put("success", true);
        res.put("message", "Đăng ký thành công.");
        res.put("email", account.getEmail());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/profile", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> profile() {
        try {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //** khoi tao mac dinh account he thong super admin
    @PostMapping(value = "/init-default-super-admin", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> init() {
        Map<String, Object> response = new HashMap<>();
        Account account = new Account();
        account.setPassword(encoder.encode("admin123"));
        account.setFaculty(null);
        account.setDob(LocalDate.parse("1988-07-22")); // gia tri Date la LocalDate
        account.setGender(Byte.parseByte("1"));
        account.setEmail("admin@gmail.com");
        account.setStatus(false);
        account.setBranchHospital(null);
        account.setFirstName("Tran");
        account.setLastName("Dai");

        Role role = roleService.findById(1);
        RoleAccount roleAccount = new RoleAccount();
        roleAccount.setAccount(account);
        roleAccount.setRole(role);

        if (accountService.deleteByEmail("admin@gmail.com")) {
            boolean savedAccount = accountService.save(account);
            boolean savedRoleAccount = roleAccountService.save(roleAccount);
            try {
                if (savedAccount && savedRoleAccount) {
                    response.put("status", HttpStatus.OK.value());
                    response.put("message", "Account Created");
                } else {
                    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    response.put("message", "Account Not Created");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }
}
