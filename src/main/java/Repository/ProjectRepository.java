package Repository;

import Entity.Project;
import api.repository.IProjectRepository;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectRepository implements IProjectRepository{

    private static Map<Integer, Project> projects = new LinkedHashMap<>();

    public void addProject(Integer projectId, String projectName, String description) {
        if (!projects.containsKey(projectId)) {
            Project project = new Project(projectId, projectName, description);
            projects.put(projectId, project);
        }
    }

    public void deleteProject(Integer projectId) {
        if (projects.containsKey(projectId)) {
            projects.remove(projectId);
        }
    }

    public void deleteAll() {
        projects.clear();
    }
    public void print() {
        projects.forEach((k, v) -> System.out.println(v));
}


    static public Map<Integer, Project> getProjects() {
        return projects;
    }
}
