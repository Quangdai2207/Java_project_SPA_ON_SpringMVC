package com.example.demo.controllers.restApiController.patient;

import com.example.demo.DTOs.doctor.ResponseFormRegistryAppointment;
import com.example.demo.entities.Account;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ** Route fetch data de render du lieu trong combox cho trang bookings khi nguoi dung chon faculti thi hien danh sach
// ** bac si tuong ung.
@RestController
@RequestMapping("/patient/api")
public class PatientRestApi {
    @Autowired
    private FacultyService facultyService;

    @Autowired
    private AccountService accountService;

    //** Fetch danh sach faculty nap du lieu vao combox faculty trang bookings
    @GetMapping(value = "/faculties", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> faculties() {
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("status", HttpStatus.OK);
            res.put("faculties", facultyService.findAll());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("status", 500);
            res.put("message", "Co loi trong qua trinh nap du lieu");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(res);
        }
    }

    //** Hien thi danh sach bac si thuoc khoa tuong ung khi nguoi dung chon faculty
    @GetMapping(value = "/doctor", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doctors(
            @RequestParam("faculty") Integer facultyID
    ) {
        Map<String, Object> res = new HashMap<>();
        List<ResponseFormRegistryAppointment> doctors = new ArrayList<>();
        List<Account> data = accountService.findAccountsByFaculty(facultyID);
        if (data != null || data.isEmpty()) {
            data.forEach(doctor -> {
                doctors.add(new ResponseFormRegistryAppointment(doctor.getId(), doctor.getFirstName(), doctor.getLastName()));
            });
        }
        try {
            res.put("status", HttpStatus.OK);
            res.put("doctors", doctors);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("status", 500);
            res.put("message", "Co loi trong qua trinh nap du lieu");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(res);
        }
    }
}
