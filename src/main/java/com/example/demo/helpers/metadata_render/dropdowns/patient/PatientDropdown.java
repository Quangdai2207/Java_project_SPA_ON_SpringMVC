package com.example.demo.helpers.metadata_render.dropdowns.patient;

import com.example.demo.DTOs.response.patient.ResponseProfileDTO;
import com.example.demo.helpers.mappers.AccountToPatientMapper;
import com.example.demo.services.account.AccountService;

public class PatientDropdown {
    public static ResponseProfileDTO profile(String email, AccountService accountService) {
        return AccountToPatientMapper.accountToPatientDTO(accountService, email);
    }
}
