package com.example.demo.services.address;

import com.example.demo.entities.Address;
import com.example.demo.repositories.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private IAddressRepository addressRepository;
    @Override
    public boolean save(Address address) {
        try {
            this.addressRepository.save(address);
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Address address) {
        try {
            this.addressRepository.save(address);
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Address> findAll() {
        return List.of();
    }

    @Override
    public List<Address> findByAccountId(String accountId) {
        return addressRepository.findByAccountId(accountId);
    }

    @Override
    public List<Address> findByFullName(String fullName) {
        return List.of();
    }

    @Override
    public List<Address> findByGender(String gender) {
        return List.of();
    }

    @Override
    public List<Address> findByEmail(String email) {
        return List.of();
    }

    @Override
    public List<Address> findByPhoneNumber(String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Address> findByCity(String city) {
        return List.of();
    }

    @Override
    public List<Address> findById(String addressId) {
        return List.of();
    }
}
