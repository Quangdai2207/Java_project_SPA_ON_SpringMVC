package com.example.demo.helpers.mappers;

import com.example.demo.DTOs.response.medicalRecord.ResponseMedicalRecordDTO;
import com.example.demo.entities.MedicalContent;
import com.example.demo.entities.MedicalRecord;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MedicalToMedicalDTO {
    private static String CastingDateSQL(Instant datetime) {
        /**
         * Kiểu DateTime của DB mySQL tương đương với kiểu Instance trong java Spring.
         * Mặc định khi thực hiện kết nối DB với MySql và mapping các table từ DB thành class entitíes thì tất cả
         * trường có kiểu Dateime của MySql được định nghĩa là Instance trong java
         */
        if (datetime == null) return "";
        LocalDate localDate = datetime
                .atZone(ZoneId.systemDefault()) // áp dụng timezone hệ thống (VD: Asia/Ho_Chi_Minh)
                .toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    private static String getStatus(int statusDB) {
        //** lay dữ liẹu trang thái bênh án
        return switch (statusDB) {
            case 1 -> "Đang chẩn đoán";
            case 2 -> "Đang điều trị";
            case 3 -> "Tái khám";
            case 4 -> "Hoàn tất";
            default -> "";
        };
    }

    public static ResponseMedicalRecordDTO medicalMapperDTO(
            MedicalRecord medical,
            List<MedicalContent> medicalContents
    ) {
        ResponseMedicalRecordDTO responseMedicalRecordDTO = new ResponseMedicalRecordDTO();
        String createdDate = CastingDateSQL(medical.getCreatedAt());
        String updatedDate = CastingDateSQL(medical.getIsUpdated());
        String status = getStatus(medical.getStatus());

        responseMedicalRecordDTO.setId(medical.getId());
        medicalContents.forEach(mc -> {
            responseMedicalRecordDTO.setContent(mc.getDescription());
        });
        responseMedicalRecordDTO.setDoctor(medical.getDoctor().getFirstName() + " " + medical.getDoctor().getLastName());
        responseMedicalRecordDTO.setPatient(medical.getPatient().getFirstName() + " " + medical.getPatient().getLastName());
        responseMedicalRecordDTO.setFaculty(medical.getDoctor().getFaculty().getName());
        responseMedicalRecordDTO.setCreatedDate(createdDate);
        responseMedicalRecordDTO.setUpdatedDate(updatedDate);
        responseMedicalRecordDTO.setStatus(status);
        return responseMedicalRecordDTO;
    }
}
