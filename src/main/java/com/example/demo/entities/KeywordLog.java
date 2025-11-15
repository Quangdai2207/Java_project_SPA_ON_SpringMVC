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
@Table(name = "Keyword_Logs")
public class KeywordLog {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "keyword_id")
    @JsonIgnore
    private Keyword keyword;

    @ColumnDefault("current_timestamp()")
    @Column(name = "searched_at")
    private Instant searchedAt;

    @Size(max = 255)
    @Column(name = "account_id")
    private String accountId;

    @Size(max = 100)
    @Column(name = "device", length = 100)
    private String device;

    @Size(max = 50)
    @Column(name = "region", length = 50)
    private String region;

}