package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.repository.ProjectRepository;

import java.util.List;

public class ProjectService implements IProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project persist(String userId, String projectName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project project = projectRepository.findOne(userId, projectName);
        if (project == null) {
            if (description == null || description.isEmpty()) return null;
            if (dateStart == null || dateStart.isEmpty()) return null;
            if (dateFinish == null || dateFinish.isEmpty()) return null;
            return projectRepository.persist(userId, projectName, description, dateStart, dateFinish);
        }
        return null;
    }

    public void remove(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return;
        Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            projectRepository.remove(project.getId());
        }


    }

    public void removeAll(String userId) {
        projectRepository.removeAll(userId);
    }

    public List<Project> findAll(String userId) {

        return projectRepository.findAll(userId);
    }

    public Project findOne(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return null;
        return projectRepository.findOne(userId, projectName);
    }

    public void merge(String userId, String oldProjectName, String projectName, String description, String dateStart, String dateFinish) {
        if (oldProjectName == null || oldProjectName.isEmpty()) return;
        Project project = projectRepository.findOne(userId, oldProjectName);
        if (project != null) {
            if (projectName == null || projectName.isEmpty()) return;
            if (description == null || description.isEmpty()) return;
            if (dateStart == null || dateStart.isEmpty()) return;
            if (dateFinish == null || dateFinish.isEmpty()) return;
            projectRepository.merge(project.getId(), projectName, description, dateStart, dateFinish);
        }

    }
}
