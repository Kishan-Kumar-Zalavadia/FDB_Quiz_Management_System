package com.fdb.backend.Services;

import com.fdb.backend.Entities.Role;
import com.fdb.backend.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    // ---------------------------------------------------------------------------------------------------
    public Role fetchRoleByRoleID(int roleId){
        return roleRepository.findByRoleID(roleId);
    }

    // ---------------------------------------------------------------------------------------------------
    public List<Role> fetchAllRoles() {
        return roleRepository.findAll();
    }

    // ---------------------------------------------------------------------------------------------------
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
