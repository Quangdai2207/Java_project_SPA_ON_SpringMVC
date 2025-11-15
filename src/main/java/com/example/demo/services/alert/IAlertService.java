package com.example.demo.services.alert;

import com.example.demo.entities.Alert;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IAlertService {
    List<Alert> findAll(Sort sort);
    Alert findById(String alertID);
}
