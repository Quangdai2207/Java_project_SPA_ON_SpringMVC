package com.example.demo.repositories;

import com.example.demo.entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKeywordRepository extends JpaRepository<Keyword, String> {
    @Query("FROM Keyword kw WHERE kw.searchCount >= :count")
    List<Keyword> search(@Param("count") Integer count);
}
