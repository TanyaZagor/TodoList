package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;

import java.util.List;


public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;

    }

    public Task persist(String userId, String projectName, String taskName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return null;
            if (description == null || description.isEmpty()) return null;
            if (dateStart == null || dateStart.isEmpty()) return null;
            if (dateFinish == null || dateFinish.isEmpty()) return null;

            return taskRepository.persist(userId, project.getId(), taskName, description, dateStart, dateFinish);
        }
        return null;
    }

    public void remove(String userId, String projectName, String taskId){
        if (projectName == null || projectName.isEmpty()) return;
        Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            taskRepository.remove(project.getId(), taskId);
        }

    }

    public void removeAll(String userId) {
        taskRepository.removeAll(userId);
    }

    public void removeAllInProject(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return;
        Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            taskRepository.removeAllInProject(project.getId());
        }
    }

    public void merge(String userId, String projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return;
        Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return;
            if (description == null || description.isEmpty()) return;
            if (dateStart == null || dateStart.isEmpty()) return;
            if (dateFinish == null || dateFinish.isEmpty()) return;
            taskRepository.merge(project.getId(),oldTaskName, taskName, description, dateStart, dateFinish);
        }

    }

    public List<Task> findAll(String userId, String projectName) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            return  taskRepository.findAll(project.getId());
        }
        return null;
    }

    public Task findOne(String userId, String projectName, String taskName) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return null;
            return  taskRepository.findOne(project.getId(), taskName);
        }
        return null;
    }
}
