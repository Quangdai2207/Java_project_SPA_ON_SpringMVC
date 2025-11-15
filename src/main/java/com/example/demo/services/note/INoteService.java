package com.example.demo.services.note;

import com.example.demo.entities.Note;
import com.example.demo.entities.Role;

import java.util.List;

public interface INoteService {
    List<Note> findAll();
    Note findById(String id);
}
