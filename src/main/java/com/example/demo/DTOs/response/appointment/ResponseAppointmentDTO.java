package com.example.demo.DTOs.response.appointment;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAppointmentDTO {
    private String id;
    private String doctor;
    private String doctorId;
    private String patientId;
    private String patientName;
    private String facultyName;
    private String date;
    private String time;
    private String content;
    private String status;
    private String branchHospitalName;
    private String branchHospitalId;
    private String createdAt;
}
