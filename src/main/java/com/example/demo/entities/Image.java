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
@Table(name = "Images")
public class Image {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @Size(max = 255)
    @Column(name = "path_name")
    private String pathName;

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