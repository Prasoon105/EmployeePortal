package com.example.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
public class EmployeeProjectResponseDTO {
    private List<Employee> employees;
    private long employeeCount;

   
}