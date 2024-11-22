package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "Employee")  
@Valid
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    @NotEmpty(message = "First name cannot be empty. Please enter a valid first name.")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty. Please enter a valid last name.")
    private String lastName;
    @Column(name = "birth_date")
    @NotNull(message = "birth_date cannot be empty.")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @NotEmpty(message = "Gender cannot be empty. Please select your gender.")
    private String gender;
    @NotEmpty(message = "Please enter your email address to proceed.")
    @Email(message = "Please provide a valid email address.")
    private String email;
    @NotEmpty(message = "contact number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits")
    private String contact;
    @Column(name = "hire_date")
    @NotNull(message = "Hire date is mandatory")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    @NotNull(message = "salary is mandatory")
    private Double salary;
    private Long pManagerId;
    private Long rManagerId;

    @NotNull(message = "Address cannot be null")
    @Valid
    @Embedded 
    private Address address;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "employee_project",
        joinColumns = @JoinColumn(name = "emp_id"),
        inverseJoinColumns = @JoinColumn(name = "p_id"))
    private Set<Project> projects = new HashSet<>();

	public Long getPManagerId() {
		return pManagerId;
	}

	public void setPManagerId(Long pManagerId) {
		this.pManagerId = pManagerId;
	}

	public Long getRManagerId() {
		return rManagerId;
	}

	public void setRManagerId(Long rManagerId) {
		this.rManagerId = rManagerId;
	}
    
    
    
}