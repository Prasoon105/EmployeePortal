package com.example.demo.service;


import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

	@Autowired
    ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
            project.setpId(id);
            return projectRepository.save(project);
    }

    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        } else {
            return false; 
        }
    }
    
    public Project assignManagerToProject(Long pId, Long pManagerId) {
    	Project project = projectRepository.findById(pId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

    	project.setpManagerId(pManagerId);

        return projectRepository.save(project);
    }
    
    public void deleteCompletedOrExpiredProjects() {
        LocalDate currentLocalDate = LocalDate.now();

        Date currentDate = Date.from(currentLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Project> allProjects = projectRepository.findAll();

        for (Project project : allProjects) {
            boolean isCompleted = "completed".equalsIgnoreCase(project.getpStatus());
            boolean isExpired = project.getpEndDate() != null && project.getpEndDate().equals(currentDate);
            System.out.println(project.toString());
            if (isCompleted || isExpired) {
            	deleteProject(project.getpId());
            }
        }
    }
    
}