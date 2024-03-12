package com.fdb.backend.Services;

import com.fdb.backend.Entities.Role;
import com.fdb.backend.Repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepo repo;

    public Role fetchRoleByRoleID(int roleId){
        return repo.findByRoleID(roleId);
    }

    public List<Role> fetchAllUsers() {
        return repo.findAll();
    }
}
