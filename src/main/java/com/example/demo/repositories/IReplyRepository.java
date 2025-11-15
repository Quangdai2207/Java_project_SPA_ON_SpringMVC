package com.example.demo.repositories;

import com.example.demo.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReplyRepository extends JpaRepository<Reply,String> {
    @Query("FROM Reply WHERE doctor.id = :doctorID")
    List<Reply> findRepliesByDoctor(@Param("doctorID") String doctorID);
}
