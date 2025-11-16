package com.example.demo.controllers.restApiController.super_admin;

import com.example.demo.classExtension.response.ResponseData;
import com.example.demo.entities.Account;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.hospital.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("SuperAdminDataSection")
@RequestMapping("super-admin/api/v1")
public class SuperAdminRestApi {
    @Autowired
    private AccountService accountService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping(value = "/data-section-main-page", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseData sectionMainPage() {
        ResponseData res = new ResponseData();
        List<Account> accounts = accountService.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        int countDoctor = accounts.stream()
                .filter(d -> d.getFaculty() != null && d.getStatus())
                .toList()
                .size();
        int countHospital = hospitalService.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).size();

        Map<String, Object> data = new HashMap<>();
        data.put("doctors", String.valueOf(countDoctor));
        data.put("accounts", accounts);
        data.put("hospitals", String.valueOf(countHospital));

        try {
            res.setMessage("success");
            res.setData(data);
            res.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.setMessage("fail");
            res.setData(null);
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return res;
    }
}
