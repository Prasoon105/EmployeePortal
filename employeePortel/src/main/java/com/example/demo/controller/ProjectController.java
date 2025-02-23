package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
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
@RequestMapping("/emp/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createProject( @Valid @RequestBody Project project){
    	
    	System.out.println(project.toString());
    	Project savedProject= projectService.createProject(project);
    	
        
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("message", "Project added successfully!");
        responseMessage.put("projectId", String.valueOf(savedProject.getpId()));
        
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable Long id, @Valid @RequestBody Project projectDetails) {
    	 Project updatedProject = projectService.updateProject(id, projectDetails);
        
        if (updatedProject != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Project updated successfully");
            response.put("project", updatedProject.toString());
            
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable Long id) {
    	projectService.deleteProject(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Project deleted successfully");
        response.put("projectId", id.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/{ProjectId}/assign-manager/{ManagerId}")
    public ResponseEntity<Map<String, String>> assignManagerToProject(
            @PathVariable Long ProjectId,
            @PathVariable Long ManagerId) {
        try {
            Project updatedProject = projectService.assignManagerToProject(ProjectId, ManagerId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Manager assigned successfully");
            response.put("projectId", String.valueOf(updatedProject.getpId()));
            response.put("ManagerId", String.valueOf(updatedProject.getpManagerId()));

            return new ResponseEntity<>(response, HttpStatus.OK); 
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
        }
    }
    
    @DeleteMapping("/delete-completed-or-expired")
    public ResponseEntity<Map<String, String>> deleteCompletedOrExpiredProjects() {
        try {
            projectService.deleteCompletedOrExpiredProjects();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Completed or expired projects deleted successfully.");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while deleting projects.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}