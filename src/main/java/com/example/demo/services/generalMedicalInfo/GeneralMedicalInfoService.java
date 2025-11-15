package com.example.demo.services.generalMedicalInfo;

import com.example.demo.entities.GeneralMedicalInfo;
import com.example.demo.repositories.IGeneralMedicalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralMedicalInfoService implements IGeneralMedicalInfoService{
    @Autowired
    private IGeneralMedicalInfo generalMedicalInfoRepository;

    @Override
    public List<GeneralMedicalInfo> findAll(Sort sort) {
        return generalMedicalInfoRepository.findAll(sort);
    }

    @Override
    public GeneralMedicalInfo findByPatientId(String patientId) {
        return generalMedicalInfoRepository.findByPatientId(patientId);
    }
}
