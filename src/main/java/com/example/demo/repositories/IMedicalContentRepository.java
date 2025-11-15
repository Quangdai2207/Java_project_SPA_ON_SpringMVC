package com.example.demo.repositories;

import com.example.demo.entities.MedicalContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicalContentRepository extends JpaRepository<MedicalContent, String> {
    @Query("FROM MedicalContent mc WHERE mc.medicalRecord.id = :medicalRecordId")
    List<MedicalContent> findContentByMedicalRecordId(@Param("medicalRecordId") String medicalRecordId);
}
