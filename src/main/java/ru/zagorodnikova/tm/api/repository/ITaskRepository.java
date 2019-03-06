package ru.zagorodnikova.tm.api.repository;


import ru.zagorodnikova.tm.entity.Task;

import java.util.List;


public interface ITaskRepository {

    Task persist(String userId, String projectId, String taskName, String description, String dateStart, String dateFinish);
    void remove(String projectId, String taskId);
    void removeAllInProject(String  projectId);
    void removeAll(String userId);
    void merge(String  projectId, String oldTaskName, String taskName, String description, String dateStart, String dateFinish);
    List<Task> findAll(String  projectId);
    Task findOne(String projectId, String taskName);

}
