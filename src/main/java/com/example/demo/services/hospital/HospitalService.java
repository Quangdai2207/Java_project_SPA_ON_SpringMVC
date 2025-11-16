package com.example.demo.services.hospital;

import com.example.demo.entities.Hospital;
import com.example.demo.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService implements IHospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> findAll(Sort sort) {
        return hospitalRepository.findAll(sort);
    }

    @Override
    public Hospital findById(String id) {
        return hospitalRepository.findById(id).orElse(null);
    }
}
