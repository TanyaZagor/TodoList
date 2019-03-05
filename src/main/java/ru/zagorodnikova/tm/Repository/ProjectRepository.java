package ru.zagorodnikova.tm.Repository;

import ru.zagorodnikova.tm.Entity.Project;
import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private static Map<String, Project> projects = Bootstrap.projects;
    private static Map<String, Task> tasks = Bootstrap.tasks;
    private String result;

    public void persist(String projectName, String description, String dateStart, String dateFinish) {
        result = null;
        Project project = new Project(projectName, description, dateStart, dateFinish);
        if (!projects.containsValue(project)) {
            projects.put(project.getId(), project);
        } else {
            result = "already there";
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
        projects.clear();
    }

    public Map<String, Project> findAll() {
        return projects;
}

    public Project findOne(String projectName){
        Map<String, Project> map = new LinkedHashMap<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName)) {
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
        final Project project = new Project(projectName, description, dateStart, dateFinish);
        projects.forEach((k, v) -> {
            if(v.getName().equals(oldProjectName)) {
                project.setId(k);
                projects.put(k, project);
            }
        });
    }
}
