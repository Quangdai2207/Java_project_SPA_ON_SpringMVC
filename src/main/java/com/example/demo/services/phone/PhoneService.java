package com.example.demo.services.phone;

import com.example.demo.entities.Phone;
import com.example.demo.repositories.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService implements IPhoneService{

    @Autowired
    private IPhoneRepository phoneRepository;
    @Override
    public boolean save(Phone phone) {
        try {
            phoneRepository.save(phone);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String PhoneID) {
        try {
            phoneRepository.deleteById(PhoneID);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Phone findByPhoneNumber(String phoneNumber) {
        return phoneRepository.findByPhoneNumber(phoneNumber);
    }
}
