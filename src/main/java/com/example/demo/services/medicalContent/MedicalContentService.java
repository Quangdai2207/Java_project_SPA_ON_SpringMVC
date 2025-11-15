package com.example.demo.services.medicalContent;

import com.example.demo.entities.MedicalContent;
import com.example.demo.repositories.IMedicalContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalContentService implements IMedicalContentService{
    @Autowired
    private IMedicalContentRepository medicalContentRepository;


    @Override
    public List<MedicalContent> findAll() {
        return medicalContentRepository.findAll();
    }

    @Override
    public List<MedicalContent> findContentByMedicalRecordId(String medicalRecordId) {
        return medicalContentRepository.findContentByMedicalRecordId(medicalRecordId);
    }

    @Override
    public MedicalContent findById(String id) {
        return medicalContentRepository.findById(id).orElse(null);
    }
}
