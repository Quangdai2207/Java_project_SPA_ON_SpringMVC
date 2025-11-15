package com.example.demo.repositories;

import com.example.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT ra.role FROM RoleAccount ra WHERE ra.account.id = :accountID")
    List<Role> getRolesByAccount(@Param("accountID") String accountID);

    @Query("SELECT ra.role FROM RoleAccount ra WHERE ra.account.email = :email")
    List<Role> getRolesByAccountEmail(String email);
}
