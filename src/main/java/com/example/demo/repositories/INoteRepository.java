package com.example.demo.repositories;

import com.example.demo.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INoteRepository extends JpaRepository<Note,String> {
    @Query("FROM Note n WHERE n.doctor.id = :doctorID")
    List<Note> findNoteByDoctor(@Param("doctorID") Integer doctorID);
}
