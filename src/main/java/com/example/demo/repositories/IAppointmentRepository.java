package com.example.demo.repositories;

import com.example.demo.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment,String> {
    // ** Get Appointments by facultyID:
    @Query("FROM Appointment WHERE faculty.id = :facultyID ")
    List<Appointment> findByFaculty(@Param("facultyID") Integer facultyID);

    // ** Get Appointment by doctor
    @Query("FROM Appointment WHERE doctor.id = :accountID")
    List<Appointment> findByDoctorId(@Param("accountID") String accountID);

    // ** Get Appointments by patient
    @Query("FROM Appointment WHERE patient.id = :accountID")
    List<Appointment> findByPatient(@Param("accountID") String accountID);
}

