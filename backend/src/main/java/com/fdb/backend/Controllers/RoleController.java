package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Role;
import com.fdb.backend.Services.RoleService;
import com.fdb.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

//----------------------------------------------------------------------------------------------------------------
// Get Role by RoleID
    @GetMapping("/{roleId}")
    public Role getRoleByID(@PathVariable int roleId) throws Exception{
        Role role = roleService.fetchRoleByRoleID(roleId);
        if (role == null) {
            // Handle the case where the user with the specified ID is not found.
            throw new Exception("Role with ID " + roleId + " not found");
        }
        return role;
    }

//----------------------------------------------------------------------------------------------------------------
// Get all roles
    @GetMapping("")
    public List<Role> getAllRoles(){
        return this.roleService.fetchAllRoles();
    }

//----------------------------------------------------------------------------------------------------------------
// Add roles
    @PostMapping("/saveRole")
    public Role addRole(@RequestBody Role role){
        return this.roleService.saveRole(role);
    }
}
