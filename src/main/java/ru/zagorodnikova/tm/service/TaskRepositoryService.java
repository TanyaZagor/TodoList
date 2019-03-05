package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.Repository.TaskRepository;

import java.util.Map;


public class TaskRepositoryService {
    private TaskRepository taskRepository;
    private String result;

    public String addTask(String projectName, String name, String description, String dateStart, String dateFinish) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        if (name.length() == 0 || description.length() == 0 || dateStart.length() == 0|| dateFinish.length() == 0) {
            return "Not enough data";
        }
        taskRepository.persist(projectName, name, description, dateStart, dateFinish);
        return result;
    }

    public String deleteTask(String projectName, String taskId){
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        taskRepository.remove(projectName, taskId);
        return result;
    }

    public String deleteAll(String projectName) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        taskRepository.removeAll(projectName);
        return result;
    }

    public String updateTask(String projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        taskRepository.merge(projectName,oldTaskName, taskName, description, dateStart, dateFinish);
        return result;
    }

    public Map<String, Task> print(String projectName) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        return  taskRepository.findAll(projectName);
    }

    public Task findOne(String projectName, String taskName) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        return  taskRepository.findOne(projectName, taskName);
    }
}
