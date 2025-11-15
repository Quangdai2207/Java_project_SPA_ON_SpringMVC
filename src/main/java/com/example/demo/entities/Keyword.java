package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Keywords")
public class Keyword {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();

    @Size(max = 255)
    @NotNull
    @Column(name = "keyword", nullable = false)
    private String keyword;

    @ColumnDefault("0")
    @Column(name = "search_count")
    private Long searchCount;

    @ColumnDefault("0")
    @Column(name = "unique_users")
    private Integer uniqueUsers;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_searched")
    private Instant lastSearched;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ColumnDefault("0")
    @Column(name = "trend_score")
    private Float trendScore;

    @ColumnDefault("0")
    @Column(name = "is_trending")
    private Boolean isTrending;

    @Size(max = 255)
    @Column(name = "category")
    private String category;

    @Size(max = 10)
    @ColumnDefault("'vi'")
    @Column(name = "language", length = 10)
    private String language;

    @Size(max = 50)
    @Column(name = "region", length = 50)
    private String region;

}