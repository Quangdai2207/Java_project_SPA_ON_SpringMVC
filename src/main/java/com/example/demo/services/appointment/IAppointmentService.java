package com.example.demo.services.appointment;

import com.example.demo.entities.Appointment;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IAppointmentService {
    List<Appointment> findAll(Sort sort);
    boolean save(Appointment appointment);
    boolean delete(String id);
    Appointment findById(String id);
    List<Appointment> findByDoctor(String accountID);
    List<Appointment> findByPatient(String accountID);
    List<Appointment> findByFaculty(Integer facultyID);

}
