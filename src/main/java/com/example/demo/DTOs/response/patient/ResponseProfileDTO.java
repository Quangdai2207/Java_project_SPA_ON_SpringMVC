package com.example.demo.DTOs.response.patient;

import com.example.demo.entities.GeneralMedicalInfo;
import com.example.demo.entities.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProfileDTO {
    String fullName;
    String email;
    String gender;
    String dateOfBirth;
    String phones;
    String addresses;
    List<MedicalRecord> medicalRecord;
    List<GeneralMedicalInfo> generalMedicalInfo;
}
