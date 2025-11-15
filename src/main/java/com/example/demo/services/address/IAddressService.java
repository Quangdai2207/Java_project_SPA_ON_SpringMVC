package com.example.demo.services.address;

import com.example.demo.entities.Address;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAddressService {
    boolean save(Address address);
    boolean delete(Address address);
    List<Address> findAll();
    List<Address> findByAccountId(String accountId);
    List<Address> findByFullName(String fullName);
    List<Address> findByGender(String gender);
    List<Address> findByEmail(String email);
    List<Address> findByPhoneNumber(String phoneNumber);
    List<Address> findByCity(String city);
    List<Address> findById(String addressId);
}
