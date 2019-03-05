package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;

import java.util.LinkedHashMap;
import java.util.Map;

public class TaskRepository implements ITaskRepository {

    private final Bootstrap bootstrap = Bootstrap.getBootstrap();
    private final Map<String, Project> projects = bootstrap.getProjects();
    private final Map<String, Task> tasks = bootstrap.getTasks();

    public void persist(String projectName, String taskName, String description, String dateStart, String dateFinish) {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        if (map.size() != 0) {
            Project project= map.get(projectName);
            Task task = new Task(bootstrap.getCurrentUser().getId(), project.getId(), taskName, description, dateStart, dateFinish);
            if (!tasks.containsValue(task)) {
                tasks.put(task.getId(), task);
            }
        }
    }

    public void remove(String  projectName, String taskName) {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        if (map.size() != 0) {
            Project project= map.get(projectName);
            tasks.entrySet().removeIf((v) -> (v.getValue().getProjectId().equals(project.getId()) && v.getValue().getName().equals(taskName)));
        }
    }

    public void merge(String projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish) {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        if (map.size() != 0) {
            Project project= map.get(projectName);
            final Task task = new Task(bootstrap.getCurrentUser().getId(), project.getId(), taskName, description, dateStart, dateFinish);
            tasks.forEach((k, v) -> {
                if(v.getProjectId().equals(project.getId()) && v.getName().equals(oldTaskName)) {
                    task.setId(k);
                    tasks.put(k, task);
                }
            });
        }

    }

    public void removeAll(String projectName) {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        if (map.size() != 0) {
            Project project= map.get(projectName);
            tasks.entrySet().removeIf((v) -> v.getValue().getProjectId().equals(project.getId()));
        }
    }

    public Map<String, Task> findAll(String projectName) {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        Map<String, Task> mapTaskByProjectId = new LinkedHashMap<>();
        if (map.size() != 0) {
            Project project= map.get(projectName);
            tasks.forEach((k, v) -> {
                if(v.getProjectId().equals(project.getId()) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                    mapTaskByProjectId.put(k, v);
                }
            });
        }
        return mapTaskByProjectId;

    }

    public Task findOne(String  projectName, String taskName) {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        Task task = null;
        if (map.size() != 0) {
            Project project= map.get(projectName);
            Map<String, Task> mapTasks = new LinkedHashMap<>();
            tasks.forEach((k, v) -> {
                if (v.getProjectId().equals(project.getId()) && v.getName().equals(taskName)) {
                    mapTasks.put(v.getProjectId(), v);
                }
            });

            if (mapTasks.size() != 0) {
                task = mapTasks.get(project.getId());
            }
        }
        return task;
    }

}
