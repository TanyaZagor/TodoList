package ru.zagorodnikova.tm.api.repository;


import ru.zagorodnikova.tm.Entity.Task;

import java.util.Map;

public interface ITaskRepository {

    String addTask(Integer projectId, String taskName, String description, String dateFinish);
    String deleteTask(Integer projectId, Integer taskId);
    String deleteAll(Integer projectId);
    Map<Integer, Task> print(Integer projectId);

}
