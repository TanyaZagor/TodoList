package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.Repository.TaskRepository;

import java.util.Map;


public class TaskRepositoryService {
    private TaskRepository taskRepository;
    private Integer pId = null;
    private Integer tId = null;
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
            pId = Integer.valueOf(projectId);
            result = taskRepository.addTask(pId, name, description, dateStart, dateFinish);
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
            tId = Integer.valueOf(taskId);
            pId = Integer.valueOf(projectId);
            result = taskRepository.deleteTask(pId, tId);
        } catch (NumberFormatException e) {
            result = "wrong id";
        }
        return result;
    }

    public String deleteAll(String projectId) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            pId = Integer.valueOf(projectId);
            result = taskRepository.deleteAll(pId);
        } catch (NumberFormatException e) {
            result = "wrong id";
        }
        return result;
    }

    public String updateTask(String projectId, String taskId, String updateId, String newData) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Integer update = Integer.valueOf(updateId);
            tId = Integer.valueOf(taskId);
            pId = Integer.valueOf(projectId);
            if (newData.length() == 0) {
                return "not enough data";
            }
            result = taskRepository.updateTask(pId, tId, update, newData);
        } catch (NumberFormatException e) {
            result = "wrong update id";
        }
        return result;
    }

    public Map<Integer, Task> print(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        pId = Integer.valueOf(projectId);
        return  taskRepository.print(pId);
    }
}
