package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Accounts")
public class Account {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "branch_hospital_id")
    @JsonIgnore
    private BranchHospital branchHospital;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dob")
    private LocalDate dob;

    @Size(max = 65)
    @Column(name = "password", length = 65)
    private String password;

    @Column(name = "gender")
    private Byte gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "faculty_id")
    @JsonIgnore
    private Faculty faculty;

    @Size(max = 255)
    @Column(name = "security_code")
    private String securityCode = UUID.randomUUID().toString().replaceAll("-", "");

    @ColumnDefault("0")
    @Column(name = "status")
    private Boolean status = false;

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