package com.example.demo.repositories;

import com.example.demo.entities.Role;
import com.example.demo.entities.RoleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleAccountRepository extends JpaRepository<RoleAccount,Integer> {
}
