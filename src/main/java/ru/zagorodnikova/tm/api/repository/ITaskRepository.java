package ru.zagorodnikova.tm.api.repository;


import ru.zagorodnikova.tm.Entity.Task;

import java.util.Map;

public interface ITaskRepository {

    void persist(String projectId, String taskName, String description, String dateStart, String dateFinish);
    void remove(String projectId, String taskId);
    void removeAll(String  projectId);
    void merge(String  projectId, String oldTaskName, String taskName, String description, String dateStart, String dateFinish);
    Map<String, Task> findAll(String  projectId);
    Task findOne(String projectId, String taskName);

}
