package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private final Map<String, Project> projects = new LinkedHashMap<>();

    public Project persist(String userId, String projectName, String description, String dateStart, String dateFinish) {
        Project project = new Project(userId, projectName, description, dateStart, dateFinish);
        projects.put(project.getId(), project);
        return project;
    }

    public void remove(String  projectId) {
        projects.remove(projectId);
    }

    public void removeAll(String userId) {
        projects.entrySet().removeIf((v) -> v.getValue().getUserId().equals(userId));
    }

    public List<Project> findAll(String userId) {
        List<Project> list = new ArrayList<>();
        projects.forEach((k, v) -> {
            if (v.getUserId().equals(userId)) {
                list.add(v);
            }
        });
        return list;
    }

    public Project findOne(String userId, String projectName){
        List<Project> list = new ArrayList<>();
        projects.forEach((k, v) -> {
            if (v.getName().equals(projectName) && v.getUserId().equals(userId)) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void merge(String projectId, String projectName, String description, String dateStart, String dateFinish) {
        projects.get(projectId).setName(projectName);
        projects.get(projectId).setDescription(description);
        projects.get(projectId).setDateStart(dateStart);
        projects.get(projectId).setDateFinish(dateFinish);
    }

}
