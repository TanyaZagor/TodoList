package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.repository.TaskRepository;

import java.util.Map;


public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String projectName, String taskName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        } else if (taskName == null || taskName.isEmpty()) {
            throw new RuntimeException();
        } else if (description == null || description.isEmpty()) {
            throw new RuntimeException();
        } else if (dateStart == null || dateStart.isEmpty()) {
            throw new RuntimeException();
        } else if (dateFinish == null || dateFinish.isEmpty()) {
            throw new RuntimeException();
        }
        taskRepository.persist(projectName, taskName, description, dateStart, dateFinish);
    }

    public void deleteTask(String projectName, String taskId){
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        }
        taskRepository.remove(projectName, taskId);
    }

    public void deleteAll(String projectName) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        }
        taskRepository.removeAll(projectName);

    }

    public void updateTask(String projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        } else if (taskName == null || taskName.isEmpty()) {
            throw new RuntimeException();
        } else if (description == null || description.isEmpty()) {
            throw new RuntimeException();
        } else if (dateStart == null || dateStart.isEmpty()) {
            throw new RuntimeException();
        } else if (dateFinish == null || dateFinish.isEmpty()) {
            throw new RuntimeException();
        }
        taskRepository.merge(projectName,oldTaskName, taskName, description, dateStart, dateFinish);

    }

    public Map<String, Task> print(String projectName) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        }
        return  taskRepository.findAll(projectName);
    }

    public Task findOne(String projectName, String taskName) {
        if (projectName == null || projectName.isEmpty()) {
            throw new RuntimeException();
        }
        return  taskRepository.findOne(projectName, taskName);
    }
}
