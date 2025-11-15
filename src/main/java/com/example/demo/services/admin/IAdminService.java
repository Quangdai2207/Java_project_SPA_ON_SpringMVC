package com.example.demo.services.admin;

import com.example.demo.entities.Account;

import java.util.List;

public interface IAdminService {
    List<Account> getAdmins(Integer roleID);
    List<Account> getDoctors(Integer roleID);
    List<Account> getPatients(Integer roleID);
    List<Account> getDoctorsByFaculty(Integer roleID, Integer facultyID);
    List<Account> getPatientsByFaculty(Integer roleID, Integer facultyID);
}
