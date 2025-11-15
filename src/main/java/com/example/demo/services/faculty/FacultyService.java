package com.example.demo.services.faculty;

import com.example.demo.entities.Faculty;
import com.example.demo.repositories.IFacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService implements IFacultyService {
    @Autowired
    private IFacultyRepository facultyRepository;

    @Override
    public List<Faculty> findAll() {
        return this.facultyRepository.findAll();
    }

    @Override
    public Faculty findById(Integer id) {
        return facultyRepository.findById(id).orElse(null);
    }
}
