package com.example.demo.repositories;

import com.example.demo.entities.KeywordLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKeyLogRepository extends JpaRepository<KeywordLog, String> {
    @Query("SELECT kl.keyword FROM KeywordLog kl WhERE kl.keyword.id = :keyword")
    List<KeywordLog> findKeyLogsByKeyword(@Param("keyword") String keyword);
}
