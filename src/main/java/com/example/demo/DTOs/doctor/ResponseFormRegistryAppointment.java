package com.example.demo.DTOs.doctor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFormRegistryAppointment {
    private String id;
    private String fName;
    private String lName;

    public ResponseFormRegistryAppointment(String id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public ResponseFormRegistryAppointment() {}
}
