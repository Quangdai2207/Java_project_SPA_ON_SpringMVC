package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "General_medical_info")
public class GeneralMedicalInfo {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Account patient;

    @Size(max = 255)
    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Size(max = 255)
    @Column(name = "past_medical_history")
    private String pastMedicalHistory;

    @Size(max = 255)
    @Column(name = "allergies")
    private String allergies;

    @Size(max = 255)
    @Column(name = "lifestyle_habits")
    private String lifestyleHabits;

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