package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;
    private final ITaskRepository taskRepository;

    public ProjectService(IProjectRepository projectRepository, ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public Project persist(String userId, String projectName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project == null) {
            if (description == null || description.isEmpty()) return null;
            if (dateStart == null || dateStart.isEmpty()) return null;
            if (dateFinish == null || dateFinish.isEmpty()) return null;
            newProject.setDescription(description);
            newProject.setUserId(userId);
            newProject.setDateStart(dateStart);
            newProject.setDateFinish(dateFinish);
            return (Project) projectRepository.persist(newProject);
        }
        return null;
    }

    public void remove(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject= new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        final Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            projectRepository.remove(project);
            taskRepository.removeAllInProject(project);
        }
    }

    public void removeAll(String userId) {
        Project project = new Project();
        project.setUserId(userId);
        projectRepository.removeAll(project);
        taskRepository.removeAll(project);
    }

    public List<AbstractEntity> findAll(String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

    public Project findOne(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        return (Project) projectRepository.findOne(newProject);
    }

    public void merge(String userId, String oldProjectName, String projectName, String description, String dateStart, String dateFinish) {
        if (oldProjectName == null || oldProjectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(oldProjectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (projectName == null || projectName.isEmpty()) return;
            if (description == null || description.isEmpty()) return;
            if (dateStart == null || dateStart.isEmpty()) return;
            if (dateFinish == null || dateFinish.isEmpty()) return;
            newProject.setId(project.getId());
            newProject.setName(projectName);
            newProject.setDescription(description);
            newProject.setDateStart(dateStart);
            newProject.setDateFinish(dateFinish);
            projectRepository.merge(newProject);
        }
    }
}
