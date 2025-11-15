package com.example.demo.services.appointment;

import com.example.demo.entities.Appointment;
import com.example.demo.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll(Sort sort) {
        return this.appointmentRepository.findAll(sort);
    }

    @Override
    public boolean save(Appointment appointment) {
        try {
            this.appointmentRepository.save(appointment);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            this.appointmentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Appointment findById(String id) {
        return this.appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> findByDoctor(String accountID) {
        return this.appointmentRepository.findByDoctorId(accountID);
    }

    @Override
    public List<Appointment> findByPatient(String accountID) {
        return this.appointmentRepository.findByPatient(accountID);
    }

    @Override
    public List<Appointment> findByFaculty(Integer facultyID) {
        return this.appointmentRepository.findByFaculty(facultyID);
    }
}
