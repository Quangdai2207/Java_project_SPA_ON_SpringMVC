package com.example.demo.services.role;

import com.example.demo.entities.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getRolesByAccountId(String accountID);
    List<Role> getRolesByEmail(String email);
    Role findById(Integer roleID);
    List<Role> findAll();

}
