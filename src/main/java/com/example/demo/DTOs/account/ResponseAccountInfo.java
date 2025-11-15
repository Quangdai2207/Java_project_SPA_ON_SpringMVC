package com.example.demo.DTOs.account;

import com.example.demo.entities.Faculty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAccountInfo {
    String first_name;
    String last_name;
    String email;
    Faculty faculty;
    String  branchHospital;
    String gender;
    String dateOfBirth;
}
