package Repository;

import Entity.Project;
import Entity.Task;

import java.util.HashMap;

public class ProjectRepository {

    private HashMap<String, Project> projects;

    public ProjectRepository() {
        projects = new HashMap<>();
    }

    public void addProject(Project project) {
        projects.put(project.getName(), project);
    }

    public void deleteProject(String name) {
        projects.remove(name);
    }

    public void deleteAll() {
        projects.clear();
    }

    public void deleteTask(String projectName, String taskName) {
        projects.get(projectName).getTasks().deleteTask(taskName);
    }

    public void addTask(String projectName, Task task) {
        projects.get(projectName).getTasks().addTask(task);
    }

    public void deleteAllTasks(String projectName) {
        projects.get(projectName).getTasks().deleteAll();
    }

    public void print() {
        projects.forEach((key, value) -> {System.out.println("Name: " + value.getName() + " Tasks: " ); value.getTasks().print();});
    }
}
