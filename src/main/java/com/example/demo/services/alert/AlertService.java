package com.example.demo.services.alert;

import com.example.demo.entities.Alert;
import com.example.demo.repositories.IAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService implements IAlertService{
    @Autowired
    private IAlertRepository alertRepository;

    @Override
    public List<Alert> findAll(Sort sort) {
        return this.alertRepository.findAll(sort);
    }

    @Override
    public Alert findById(String alertID) {
        return alertRepository.findById(alertID).orElse(null);
    }
}
