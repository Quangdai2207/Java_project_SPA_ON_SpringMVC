package com.example.demo.helpers.mappers;

import com.example.demo.DTOs.response.patient.ResponseProfileDTO;
import com.example.demo.entities.*;
import com.example.demo.services.account.AccountService;

import java.util.List;

public class AccountToPatientMapper {
    public static ResponseProfileDTO accountToPatientDTO(
            AccountService accountService,
            String email
    ) {
        Account account = accountService.findAccountByEmail(email);
        Address address = accountService.getListAddress(account.getId()).get(0);
        List<Phone> phones = accountService.getListPhoneByAccount(account.getId());
        List<GeneralMedicalInfo> generalMedicalInfos = accountService.getListGeneralMedicalInfo(account.getId());
        List<MedicalRecord> medicalRecords = accountService.getListMedicalRecord(account.getId());

        String add = address.getAddressNumber() + " "
                + address.getStreet() + ", "
                + "P." + address.getWard() + ", "
                + "Q." + address.getDistrict() + ", "
                + "Tp." + address.getProvince();

        ResponseProfileDTO responsePatientDTO = new ResponseProfileDTO();
        responsePatientDTO.setGender(account.getGender() == 0 ? "Nam" : "Nu");
        responsePatientDTO.setEmail(account.getEmail());
        responsePatientDTO.setFullName(account.getFirstName() + " " + account.getLastName());
        responsePatientDTO.setDateOfBirth(account.getDob().toString());
        responsePatientDTO.setPhones(!phones.isEmpty() ? phones.get(0).getPhoneNumber() : "-/-");
        responsePatientDTO.setAddresses(add);
        responsePatientDTO.setMedicalRecord(medicalRecords);
        responsePatientDTO.setGeneralMedicalInfo(generalMedicalInfos);

        return responsePatientDTO;
    }
}
