package com.example.demo.DTOs.response.medicalRecord;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMedicalRecordDTO {
    private String id;
    private String patient;
    private String doctor;
    private String faculty;
    private String status;
    private String content;
    private String createdDate;
    private String updatedDate;
}
