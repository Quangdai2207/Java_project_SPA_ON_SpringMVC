package com.example.demo.repositories;

import com.example.demo.entities.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IInvoiceRepository extends CrudRepository<Invoice, String> {
    @Query("FROM Invoice iv WHERE iv.patient.id = :patientId ORDER BY iv.createdAt DESC")
    List<Invoice> findByPatientId(@Param("patientId") String patientId);

    @Query("FROM Invoice iv WHERE iv.doctor.id = :doctorId ORDER BY iv.createdAt DESC")
    List<Invoice> findByDoctorId(@Param("doctorId") String doctorId);

    @Query("FROM Invoice iv WHERE iv.status = :status ORDER BY iv.createdAt DESC")
    List<Invoice> findByStatus(@Param("status") boolean status);

    @Query("FROM Invoice iv WHERE iv.amount BETWEEN :from AND : to ORDER BY iv.createdAt DESC")
    List<Invoice> findBetween(@Param("from") BigDecimal from, @Param("to") BigDecimal to);

    @Query("FROM Invoice iv WHERE iv.createdAt BETWEEN :from AND :to ORDER BY iv.createdAt DESC ")
    List<Invoice> findByDates(@Param("from")LocalDate from, @Param("to") LocalDate to);


}
