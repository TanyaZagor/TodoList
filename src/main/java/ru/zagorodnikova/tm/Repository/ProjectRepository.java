package ru.zagorodnikova.tm.Repository;

import ru.zagorodnikova.tm.Entity.Project;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private static Map<Integer, Project> projects = Bootstrap.projects;
    private String result;

    public String addProject(Integer projectId, String projectName, String description, String dateFinish) {
        result = null;
        if (!projects.containsKey(projectId)) {
            Project project = new Project(projectId, projectName, description, dateFinish);
            projects.put(projectId, project);
        } else {
            result = "already there";
        }
        return result;
    }

    public String deleteProject(Integer projectId) {
        result = null;
        if (projects.containsKey(projectId)) {
            projects.remove(projectId);
        } else {
            result = "no project with this id";
        }
        return result;
    }

    public void deleteAll() {
        projects.clear();
    }
    public Map<Integer, Project> print() {
        return projects;
}


    static Map<Integer, Project> getProjects() {
        return projects;
    }


    public String updateProject(Integer projectId, Integer updateId, String newData) {
        result = null;
        if (projects.containsKey(projectId)) {
            switch (updateId) {
                case 1:
                    projects.get(projectId).setName(newData);
                    break;
                case 2:
                    projects.get(projectId).setDescription(newData);
                    break;
                case 3:
                    projects.get(projectId).setDateFinish(newData);
                    break;
                default:
                    result = "wrong update id";
            }
        } else {
            result = "Wrong id";
        }
        return result;
    }
}
