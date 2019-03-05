package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.repository.ProjectRepository;

import java.util.Map;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(String projectName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        } else if (description == null || description.isEmpty()) {
            throw new RuntimeException();
        } else if (dateStart == null || dateStart.isEmpty()) {
            throw new RuntimeException();
        } else if (dateFinish == null || dateFinish.isEmpty()) {
            throw new RuntimeException();
        }
        projectRepository.persist(projectName, description, dateStart, dateFinish);
    }

    public void deleteProject( String projectName) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        }
        projectRepository.remove(projectName);

    }

    public void deleteAll() {

        projectRepository.removeAll();
    }

    public Map<String, Project> print() {

        return projectRepository.findAll();
    }

    public Project findOne(String projectName) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        }
        return projectRepository.findOne(projectName);
    }

    public void updateProject(String oldProjectName, String projectName, String description, String dateStart, String dateFinish) {
        if (oldProjectName == null || oldProjectName.isEmpty()) {
            throw new RuntimeException();
        } else if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        } else if (description == null || description.isEmpty()) {
            throw new RuntimeException();
        } else if (dateStart == null || dateStart.isEmpty()) {
            throw new RuntimeException();
        } else if (dateFinish == null || dateFinish.isEmpty()) {
            throw new RuntimeException();
        }
        projectRepository.merge(oldProjectName, projectName, description, dateStart, dateFinish);

    }
}
