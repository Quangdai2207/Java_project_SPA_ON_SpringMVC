package com.example.demo.controllers.restApiController.dropdowns.patient;

import com.example.demo.DTOs.response.appointment.ResponseAppointmentDTO;
import com.example.demo.DTOs.response.medicalRecord.ResponseMedicalRecordDTO;
import com.example.demo.DTOs.response.patient.ResponseProfileDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Appointment;
import com.example.demo.entities.MedicalContent;
import com.example.demo.entities.MedicalRecord;
import com.example.demo.helpers.mappers.AppointmentToAppointmentDTO;
import com.example.demo.helpers.mappers.MedicalToMedicalDTO;
import com.example.demo.helpers.metadata_render.dropdowns.patient.PatientDropdown;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.appointment.AppointmentService;
import com.example.demo.services.medicalContent.MedicalContentService;
import com.example.demo.services.medicalRecord.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patient")
public class RestApiContextPatient {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private MedicalContentService medicalContentService;
    ;

    // ** Context metadata cho section profile dropdown patient
    @GetMapping(value = "/profile", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sectionProfile(
            Authentication auth
    ) {
        Map<String, Object> res = new HashMap<>();
        String email = null;
        if (auth != null) email = auth.getName();
        ResponseProfileDTO profile = PatientDropdown.profile(email, accountService);
        try {
            res.put("message", "OK");
            res.put("profile", profile);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //** Context metadata cho section appointment dropdown patient
    @GetMapping(value = "/appointment", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sectionAppointment(Authentication auth) {
        Map<String, Object> res = new HashMap<>();
        String email = null;
        if (auth != null) email = auth.getName();
        Account account = accountService.findAccountByEmail(email);
        List<Appointment> appointments = appointmentService.findByPatient(account.getId());
        List<ResponseAppointmentDTO> responseAppointmentDTOs = new ArrayList<>();
        appointments.forEach(appointment -> {
            ResponseAppointmentDTO responseAppointmentDTO = AppointmentToAppointmentDTO.toResponseAppointmentDTO(appointment);
            responseAppointmentDTOs.add(responseAppointmentDTO);
        });
        res.put("message", "OK");
        res.put("appointments", responseAppointmentDTOs);
        try {
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ** Context metadata cho section profile_appointment dropdown patient
    @GetMapping(value = "/profile_appointment", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sectionProfileAppointment(Authentication auth) {
        Map<String, Object> res = new HashMap<>();
        String email = null;
        if (auth != null) email = auth.getName();
        Account account = accountService.findAccountByEmail(email);
        List<MedicalRecord> medicalRecords = medicalRecordService.findByPatientId(account.getId());
        List<ResponseMedicalRecordDTO> responseAppointmentDTOS = new ArrayList<>();
        medicalRecords.forEach(medical -> {
            List<MedicalContent> medicalContents = medicalContentService.findContentByMedicalRecordId(medical.getId());
            responseAppointmentDTOS.add(MedicalToMedicalDTO.medicalMapperDTO(medical, medicalContents));
        });
        res.put("message", "OK");
        res.put("medicalRecords", responseAppointmentDTOS);
        try {
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ** Context metadata cho section alert dropdown patient
    @GetMapping(value = "/alert", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sectionAlert() {
        return null;
    }
}
