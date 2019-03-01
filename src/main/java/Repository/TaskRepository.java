package Repository;

import Entity.Project;
import Entity.Task;
import api.repository.ITaskRepository;

import java.util.Date;
import java.util.Map;

public class TaskRepository implements ITaskRepository {

    private Map<Integer, Project> projects;

    public TaskRepository() {
        projects = ProjectRepository.getProjects();
    }

    public void addTask(Integer projectId, Integer taskId, String taskName, String description, Date dateFinish) {
        if (!projects.get(projectId).getTasks().containsKey(taskId)) {
            Task task = new Task(taskId, taskName, description, dateFinish);
            projects.get(projectId).getTasks().put(taskId, task);
        }
    }

    public void deleteTask(Integer projectId, Integer taskId) {
        if (projects.get(projectId).getTasks().containsKey(taskId)) {
            projects.get(projectId).getTasks().remove(taskId);
        }

    }

    public void deleteAll(Integer projectId) {
        projects.get(projectId).getTasks().clear();
    }

    public void print(Integer projectId) {
        projects.get(projectId).getTasks().forEach((k, v) -> System.out.println(v));
    }


}
