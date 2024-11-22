package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor 
@Valid
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depId;
    @NotEmpty(message = "Department name cannot be empty. Please enter a valid department name.")
    private String depName;
    private Long rManagerId;
    
}