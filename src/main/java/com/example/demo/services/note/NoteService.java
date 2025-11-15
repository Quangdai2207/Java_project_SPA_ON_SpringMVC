package com.example.demo.services.note;

import com.example.demo.entities.Note;
import com.example.demo.entities.Role;
import com.example.demo.repositories.INoteRepository;
import com.example.demo.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {
    @Autowired
    private INoteRepository noteRepository;

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(String id) {
        return noteRepository.findById(id).orElse(null);
    }
}
