package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/emp/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createDepartment(@Valid @RequestBody Department department) {
    	Department savedDepartment = departmentService.addDepartment(department);
        
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("message", "Department added successfully!");
        responseMessage.put("departmentId", String.valueOf(savedDepartment.getDepId()));
        
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable Long id, @Valid @RequestBody Department departmentDetails) {
        Department updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
        
        if (updatedDepartment != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Department updated successfully");
            response.put("department", updatedDepartment.toString());
            
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDepartment(@PathVariable Long id)  {
    	departmentService.deleteDepartment(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Department deleted successfully");
        response.put("departmentId", id.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/{depId}/assign-manager/{rManagerId}")
    public ResponseEntity<Map<String, String>> assignManagerToDepartment(
            @PathVariable Long depId,
            @PathVariable Long rManagerId) {
        try {
            Department updatedDepartment = departmentService.assignManagerToDepartment(depId, rManagerId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Manager assigned successfully");
            response.put("departmentId", String.valueOf(updatedDepartment.getDepId()));
            response.put("rManagerId", String.valueOf(updatedDepartment.getRManagerId()));

            return new ResponseEntity<>(response, HttpStatus.OK); 
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
        }
    }
  }