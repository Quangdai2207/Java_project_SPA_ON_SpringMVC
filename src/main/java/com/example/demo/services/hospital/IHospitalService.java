package com.example.demo.services.hospital;

import com.example.demo.entities.Hospital;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IHospitalService {
    List<Hospital> findAll(Sort sort);
    Hospital findById(String id);
}
