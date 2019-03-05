package ru.zagorodnikova.tm.api.repository;


import ru.zagorodnikova.tm.entity.Task;

import java.util.Map;

public interface ITaskRepository {

    void persist(String projectName, String taskName, String description, String dateStart, String dateFinish);
    void remove(String projectName, String taskId);
    void removeAll(String  projectName);
    void merge(String  projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish);
    Map<String, Task> findAll(String  projectName);
    Task findOne(String projectName, String taskName);

}
