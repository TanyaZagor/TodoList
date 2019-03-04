package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.Repository.TaskRepository;

import java.util.Map;


public class TaskRepositoryService {
    private TaskRepository taskRepository;
    private String result;

    public String addTask(String projectId, String name, String description, String dateStart, String dateFinish) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        if (name.length() == 0 || description.length() == 0 || dateStart.length() == 0|| dateFinish.length() == 0) {
            return "Not enough data";
        }
        try {
            taskRepository.persist(projectId, name, description, dateStart, dateFinish);
        } catch (NumberFormatException e) {
            result = "wrong id";
        }
        return result;
    }

    public String deleteTask(String projectId, String taskId){
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            taskRepository.remove(projectId, taskId);
        } catch (NumberFormatException e) {
            result = "wrong id";
        }
        return result;
    }

    public String deleteAll(String projectName) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            taskRepository.removeAll(projectName);
        } catch (NumberFormatException e) {
            result = "wrong id";
        }
        return result;
    }

    public String updateTask(String projectId, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            taskRepository.merge(projectId,oldTaskName, taskName, description, dateStart, dateFinish);
        } catch (NumberFormatException e) {
            result = "wrong update id";
        }
        return result;
    }

    public Map<String, Task> print(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        return  taskRepository.findAll(projectId);
    }
}
