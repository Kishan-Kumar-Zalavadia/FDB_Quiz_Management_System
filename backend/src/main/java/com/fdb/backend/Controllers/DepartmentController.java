package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Department;
import com.fdb.backend.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    // ---------------------------------------------------------------------------------------------------
//    Get all Departments
    @GetMapping("")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // ---------------------------------------------------------------------------------------------------
//    Add department
    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department addedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
    }


    // ---------------------------------------------------------------------------------------------------
//    Get Department by ID
    @GetMapping("/{departmentID}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer departmentID) {
        Optional<Department> department = departmentService.fetchDepartmentById(departmentID);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}