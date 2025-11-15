package com.example.demo.services.faculty;

import com.example.demo.entities.Faculty;

import java.util.List;

public interface IFacultyService {
    List<Faculty> findAll();
    Faculty findById(Integer id);
}
