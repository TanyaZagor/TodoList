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
        if (projectName == null || projectName.isEmpty()) return;
        if (description == null || description.isEmpty()) return;
        if (dateStart == null || dateStart.isEmpty()) return;
        if (dateFinish == null || dateFinish.isEmpty()) return;
        projectRepository.persist(projectName, description, dateStart, dateFinish);
    }

    public void deleteProject( String projectName) {
        if (projectName == null || projectName.isEmpty()) return;
        projectRepository.remove(projectName);

    }

    public void deleteAll() {

        projectRepository.removeAll();
    }

    public Map<String, Project> print() {

        return projectRepository.findAll();
    }

    public Project findOne(String projectName) {
        if (projectName == null || projectName.isEmpty()) return null;
        return projectRepository.findOne(projectName);
    }

    public void updateProject(String oldProjectName, String projectName, String description, String dateStart, String dateFinish) {
        if (oldProjectName == null || oldProjectName.isEmpty()) return;
        if (projectName == null || projectName.isEmpty()) return;
        if (description == null || description.isEmpty()) return;
        if (dateStart == null || dateStart.isEmpty()) return;
        if (dateFinish == null || dateFinish.isEmpty()) return;
        projectRepository.merge(oldProjectName, projectName, description, dateStart, dateFinish);

    }
}
