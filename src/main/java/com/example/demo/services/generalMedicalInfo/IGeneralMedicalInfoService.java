package com.example.demo.services.generalMedicalInfo;

import com.example.demo.entities.GeneralMedicalInfo;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IGeneralMedicalInfoService {
    List<GeneralMedicalInfo> findAll(Sort sort);
    GeneralMedicalInfo findByPatientId(String patientId);
}
