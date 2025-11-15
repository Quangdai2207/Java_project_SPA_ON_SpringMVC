package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Hospital {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Byte type;

    @Size(max = 255)
    @Column(name = "director_name")
    private String directorName;

    @Size(max = 255)
    @Column(name = "tax_code")
    private String taxCode;

    @Size(max = 255)
    @Column(name = "website")
    private String website;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "contact_phone")
    private String contactPhone;

    @Size(max = 255)
    @Column(name = "license_number")
    private String licenseNumber;

    @Size(max = 255)
    @Column(name = "fax")
    private String fax;

    @Column(name = "established_date")
    private LocalDate establishedDate;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @ColumnDefault("current_timestamp()")
    @Column(name = "is_updated")
    private Instant isUpdated = Instant.now();

    @ColumnDefault("0")
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

}