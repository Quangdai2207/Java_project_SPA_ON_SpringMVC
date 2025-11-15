package com.example.demo.services.role;

import com.example.demo.entities.Role;
import com.example.demo.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> getRolesByAccountId(String accountID) {
        return roleRepository.getRolesByAccount(accountID);
    }

    @Override
    public List<Role> getRolesByEmail(String email) {
        return roleRepository.getRolesByAccountEmail(email);
    }

    @Override
    public Role findById(Integer roleID) {
        return roleRepository.findById(roleID).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
