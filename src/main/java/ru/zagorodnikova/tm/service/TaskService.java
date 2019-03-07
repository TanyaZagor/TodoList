package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;


public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;
    private final IProjectRepository projectRepository;

    public TaskService(ITaskRepository taskRepository, IProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;

    }

    public AbstractEntity persist(String userId, String projectName, String taskName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return null;
            if (description == null || description.isEmpty()) return null;
            if (dateStart == null || dateStart.isEmpty()) return null;
            if (dateFinish == null || dateFinish.isEmpty()) return null;
            Task task = new Task(userId, project.getId(), taskName, description, dateStart, dateFinish);
            return taskRepository.persist(task);
        }
        return null;
    }

    public void remove(String userId, String projectName, String taskName){
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            Task task = new Task();
            task.setUserId(userId);
            task.setProjectId(project.getId());
            task.setName(taskName);
            taskRepository.remove(task);
        }

    }

    public void removeAll(String userId) {
        Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }

    public void removeAllInProject(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            Task task = new Task();
            task.setProjectId(project.getId());
            taskRepository.removeAllInProject(task);
        }
    }

    public void merge(String userId, String projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return;
            if (description == null || description.isEmpty()) return;
            if (dateStart == null || dateStart.isEmpty()) return;
            if (dateFinish == null || dateFinish.isEmpty()) return;
            Task task = (Task) findOne(userId, projectName, oldTaskName);
            task.setName(taskName);
            task.setDescription(description);
            task.setDateStart(dateStart);
            task.setDateFinish(dateFinish);
            taskRepository.merge(task);
        }

    }

    public List<AbstractEntity> findAll(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            Task task = new Task();
            task.setProjectId(project.getId());
            return  taskRepository.findAll(task);
        }
        return null;
    }

    public AbstractEntity findOne(String userId, String projectName, String taskName) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return null;
            Task task = new Task();
            task.setProjectId(project.getId());
            task.setName(taskName);
            return taskRepository.findOne(task);
        }
        return null;
    }
}
