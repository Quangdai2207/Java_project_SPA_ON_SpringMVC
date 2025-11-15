package com.example.demo.repositories;

import com.example.demo.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPhoneRepository extends JpaRepository<Phone,String> {
    @Query("FROM Phone WHERE account.id = :accountID")
    List<Phone> findPhonesByAccountID(@Param("accountID") Integer accountID);

    @Query("FROM Phone WHERE phoneNumber = :phoneNumber")
    Phone findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
