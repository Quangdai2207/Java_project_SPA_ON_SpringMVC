package com.example.demo.services.medicalContent;

import com.example.demo.entities.MedicalContent;

import java.util.List;

public interface IMedicalContentService {
    List<MedicalContent> findAll();
    List<MedicalContent> findContentByMedicalRecordId(String medicalRecordId);
    MedicalContent findById(String id);
}
