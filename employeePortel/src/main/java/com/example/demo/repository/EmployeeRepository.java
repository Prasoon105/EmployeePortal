package com.example.demo.repository;

import com.example.demo.model.Employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { 
	
	List<Employee> findByDepartment_DepId(Long departmentId);
	
	List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);
	
	long countByDepartment_DepId(Long departmentId);
	
	List<Employee> findByProjects_pId(Long projectId);
	
	long countByProjects_pId(Long projectId);
	
	@Query("SELECT COUNT(e) FROM Employee e JOIN e.projects p WHERE p.pId = :projectId")
	long countByProjectspId(@Param("projectId") Long projectId);
}