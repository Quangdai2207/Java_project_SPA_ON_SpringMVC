package com.example.demo.services.phone;

import com.example.demo.entities.Phone;

public interface IPhoneService {
    boolean save(Phone phone);
    boolean delete(String PhoneID);
    Phone findByPhoneNumber(String phoneNumber);
}
