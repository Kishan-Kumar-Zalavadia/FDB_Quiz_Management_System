package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByRoleID(int roleID);

    List<Role> findAll();
}
