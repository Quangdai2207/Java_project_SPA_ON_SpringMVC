package com.example.demo.helpers.mappers;

import com.example.demo.DTOs.response.appointment.ResponseAppointmentDTO;
import com.example.demo.entities.Appointment;

public class AppointmentToAppointmentDTO {
    public static ResponseAppointmentDTO  toResponseAppointmentDTO(Appointment appointment) {
        ResponseAppointmentDTO responseAppointmentDTO = new ResponseAppointmentDTO();
        String status = "";
        if (appointment.getStatus() == 1) {
            status = "Dã xác nhận";
        } else if (appointment.getStatus() == 0) {
            status = "Đợi duyệt";
        } else if (appointment.getStatus() == 2) {
            status = "Đã huỷ";
        }

        responseAppointmentDTO.setId(appointment.getId());
        responseAppointmentDTO.setDate(appointment.getDate().toString());
        responseAppointmentDTO.setTime(appointment.getTime().toString());
        responseAppointmentDTO.setFacultyName(appointment.getDoctor().getFaculty().getName());
        responseAppointmentDTO.setContent(appointment.getContent());
        responseAppointmentDTO.setDoctor(appointment.getDoctor().getLastName() + " " + appointment.getDoctor().getFirstName());
        responseAppointmentDTO.setDoctorId(appointment.getDoctor().getId());
        responseAppointmentDTO.setPatientId(appointment.getPatient().getId());
        responseAppointmentDTO.setPatientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
        responseAppointmentDTO.setBranchHospitalId(appointment.getBranchHospital().getId());
        responseAppointmentDTO.setBranchHospitalName(appointment.getBranchHospital().getBranchName());
        responseAppointmentDTO.setStatus(status);
        responseAppointmentDTO.setCreatedAt(appointment.getCreatedAt().toString());

        return responseAppointmentDTO;
    }
}
