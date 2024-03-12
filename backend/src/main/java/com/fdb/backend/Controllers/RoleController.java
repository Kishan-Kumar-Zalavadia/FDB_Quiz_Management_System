package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Role;
import com.fdb.backend.Services.RoleService;
import com.fdb.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;


    // Get Role by RoleID
    @GetMapping("/{roleId}")
    public Role getRoleByID(@PathVariable int roleId) throws Exception{
        Role role = service.fetchRoleByRoleID(roleId);
        if (role == null) {
            // Handle the case where the user with the specified ID is not found.
            throw new Exception("Role with ID " + roleId + " not found");
        }
        return role;
    }

    // Get all roles
    @GetMapping("")
    public List<Role> getAllRoles(){
        return this.service.fetchAllUsers();
    }
}
