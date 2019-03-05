package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private final Bootstrap bootstrap = Bootstrap.getBootstrap();
    private final Map<String, Project> projects = bootstrap.getProjects();
    private final Map<String, Task> tasks = bootstrap.getTasks();

    public void persist(String projectName, String description, String dateStart, String dateFinish) {
        Project project = new Project(bootstrap.getCurrentUser().getId(), projectName, description, dateStart, dateFinish);
        if (!projects.containsValue(project)) {
            projects.put(project.getId(), project);
        }
    }

    public void remove(String  projectName) {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName)) {
                map.put(v.getName(), v);
            }
        });
        if (map.size() != 0) {
            Project project= map.get(projectName);
            tasks.entrySet().removeIf((v) -> v.getValue().getProjectId().equals(project.getId()));
            projects.entrySet().removeIf((v) -> v.getValue().getName().equals(projectName));
        }
    }

    public void removeAll() {
        projects.entrySet().removeIf((v) -> v.getValue().getUserId().equals(bootstrap.getCurrentUser().getId()));
    }

    public Map<String, Project> findAll() {
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        return map;
    }

    public Project findOne(String projectName){
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                map.put(v.getName(), v);
            }
        });
        Project project = null;
        if (map.size() != 0) {
            project= map.get(projectName);
        }
        return project;
    }

    public void merge(String oldProjectName, String projectName, String description, String dateStart, String dateFinish) {
        final Project project = new Project(bootstrap.getCurrentUser().getId(), projectName, description, dateStart, dateFinish);
        projects.forEach((k, v) -> {
            if(v.getName().equals(oldProjectName) && v.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                project.setId(k);
                projects.put(k, project);
            }
        });
    }
}
