package ru.zagorodnikova.tm.Repository;

import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskRepository implements ITaskRepository {

    private Map<String, Task> tasks = Bootstrap.tasks;
    private String result;


    public void persist(String projectId, String taskName, String description, String dateStart, String dateFinish) {
        result = null;
        Task task = new Task(projectId, taskName, description, dateStart, dateFinish);
        if (!tasks.containsValue(task)) {
            tasks.put(task.getId(), task);
        } else {
            result = "already there";
        }
    }

    public void remove(String  projectId, String taskName) {
        tasks.entrySet().removeIf((v) -> (v.getValue().getProjectId().equals(projectId) && v.getValue().getName().equals(taskName)));
    }

    public void merge(String projectId, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {
        final Task task = new Task(projectId, taskName, description, dateStart, dateFinish);
        tasks.forEach((k, v) -> {
            if(v.getProjectId().equals(projectId) && v.getName().equals(oldTaskName)) {
                task.setId(k);
                tasks.put(k, task);
            }
        });
    }

    public void removeAll(String projectId) {
        tasks.entrySet().removeIf((v) -> v.getValue().getProjectId().equals(projectId));
    }

    public Map<String, Task> findAll(String projectId) {
        Map<String, Task> map = new LinkedHashMap<>();
        tasks.forEach((k, v) -> {
            if(v.getProjectId().equals(projectId)) {
                map.put(k, v);
            }
        });
        return map;

    }

    public Task findOne(String  projectId, String taskName) {
        List list = tasks.entrySet().stream()
                .filter(x -> (x.getValue().getProjectId().equals(projectId) && x.getValue().getName().equals(taskName)))
                .collect(Collectors.toList());
        Task task = (Task) list.get(0);
        return task;
    }

}
