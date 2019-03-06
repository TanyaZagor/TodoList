package ru.zagorodnikova.tm.api.service;

import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

public interface ITaskService {
    Task persist(String userId, String projectName, String taskName, String description, String dateStart, String dateFinish);
    void remove(String userId, String projectName, String taskId);
    void removeAllInProject(String userId, String projectName);
    void removeAll(String userId);
    void merge(String userId, String projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish);
    List<Task> findAll(String userId, String projectName);
    Task findOne(String userId, String projectName, String taskName);
}
