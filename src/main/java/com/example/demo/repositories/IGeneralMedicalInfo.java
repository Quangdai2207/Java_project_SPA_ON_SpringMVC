package com.example.demo.repositories;

import com.example.demo.entities.GeneralMedicalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGeneralMedicalInfo extends JpaRepository<GeneralMedicalInfo, String> {
    @Query("FROM GeneralMedicalInfo gi WHERE gi.patient.id = :patientId ")
    GeneralMedicalInfo findByPatientId(@Param("patientId") String patientId);
}
