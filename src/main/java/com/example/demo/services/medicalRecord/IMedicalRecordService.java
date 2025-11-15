package com.example.demo.services.medicalRecord;

import com.example.demo.entities.MedicalRecord;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecord> findAll(Sort sort);
    List<MedicalRecord> findByPatientId(String patientId);
}
