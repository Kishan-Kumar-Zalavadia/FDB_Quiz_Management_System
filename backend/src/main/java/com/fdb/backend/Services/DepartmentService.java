package com.fdb.backend.Services;

import com.fdb.backend.Entities.Department;
import com.fdb.backend.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // ---------------------------------------------------------------------------------------------------
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // ---------------------------------------------------------------------------------------------------
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // ---------------------------------------------------------------------------------------------------
    public Optional<Department> fetchDepartmentById(Integer departmentID) {
        return departmentRepository.findById(departmentID);
    }
}