package com.example.demo.DTOs.response.GeneralMedicalInfo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGeneralMedicalInfoDTO {
    String patientName;
    String bloodType;
    String height;
    String weight;
    String pastMedicalHistory;
    String allergies;
    String lifeStyle;
}
