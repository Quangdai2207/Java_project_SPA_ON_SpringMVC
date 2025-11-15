package com.example.demo.services.medicalRecord;

import com.example.demo.entities.MedicalRecord;
import com.example.demo.repositories.IMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {
    @Autowired
    private IMedicalRecord medicalRecordRepository;

    @Override
    public List<MedicalRecord> findAll(Sort sort) {
        return medicalRecordRepository.findAll(sort);
    }

    @Override
    public List<MedicalRecord> findByPatientId(String patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }
}
