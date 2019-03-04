package ru.zagorodnikova.tm.Repository;

import ru.zagorodnikova.tm.Entity.Project;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private static Map<String, Project> projects = Bootstrap.projects;
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
        projects.entrySet().removeIf((v) -> v.getValue().getName().equals(projectName));
    }

    public void removeAll() {
        projects.clear();
    }

    public Map<String, Project> print() {
        return projects;
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
