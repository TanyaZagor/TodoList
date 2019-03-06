package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository implements ITaskRepository {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public Task persist(String userId, String projectId, String taskName, String description, String dateStart, String dateFinish) {
        Task task = new Task(userId, projectId, taskName, description, dateStart, dateFinish);
        if (!tasks.containsValue(task)) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public void remove(String  projectId, String taskName) {
        tasks.entrySet().removeIf((v) -> (v.getValue().getProjectId().equals(projectId) && v.getValue().getName().equals(taskName)));
    }

    public void merge(String projectId, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {

        tasks.forEach((k, v) -> {
            if(v.getProjectId().equals(projectId) && v.getName().equals(oldTaskName)) {
                v.setName(taskName);
                v.setDescription(description);
                v.setDateStart(dateStart);
                v.setDateFinish(dateFinish);
            }
        });
    }


    public void removeAll(String userId) {
        tasks.entrySet().removeIf((v) -> v.getValue().getUserId().equals(userId));
    }

    public void removeAllInProject(String projectId) {
        tasks.entrySet().removeIf((v) -> v.getValue().getProjectId().equals(projectId));
    }

    public List<Task> findAll(String projectId) {
        List<Task> list = new ArrayList<>();
        tasks.forEach((k, v) -> {
            if(v.getProjectId().equals(projectId)) {
                list.add(v);
            }
        });

        return list;

    }

    public Task findOne(String  projectId, String taskName) {
        List<Task> list = new ArrayList<>();
        tasks.forEach((k, v) -> {
            if(v.getProjectId().equals(projectId) && v.getName().equals(taskName)) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
