package com.example.demo.repositories;

import com.example.demo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressRepository extends JpaRepository<Address,String> {
    @Query("FROM Address add WHERE add.account.id = :accountId")
    List<Address> findByAccountId(@Param("accountId") String accountId);
}
