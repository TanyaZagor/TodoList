package service;

import Repository.TaskRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskRepositoryService {
    private TaskRepository taskRepository;
    private Integer pId = null;
    private Integer tId = null;
    private String result;

    public String addTask(String projectId, String taskId,String name, String description, String date) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        DateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy", Locale.ENGLISH);
        try {
            tId = Integer.valueOf(taskId);
            pId = Integer.valueOf(projectId);
            Date dateFinish = dateFormat.parse(date);
            taskRepository.addTask(pId, tId, name, description, dateFinish);
        } catch (NumberFormatException e) {
            result = "wrong id";
        } catch (ParseException e) {
            result = "wrong date";
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
            taskRepository.deleteTask(pId, tId);
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
            taskRepository.deleteAll(pId);
        } catch (NumberFormatException e) {
            result = "wrong id";
        }
        return result;
    }
    public String print(String projectId) {
        result = null;
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            pId = Integer.valueOf(projectId);
            taskRepository.print(pId);
        } catch (NumberFormatException e) {
            result = "wrong id";
        }
        return result;
    }
}
