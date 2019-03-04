package ru.zagorodnikova.tm.Repository;

import ru.zagorodnikova.tm.Entity.Project;
import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;

import java.util.Map;

public class TaskRepository implements ITaskRepository {

    private Map<Integer, Project> projects = ProjectRepository.getProjects();
    private String result;
    private Integer count = 10;


    public String addTask(Integer projectId, String taskName, String description, String dateFinish) {
        result = null;
        Task task = new Task(count++, taskName, description, dateFinish);
        if (!projects.get(projectId).getTasks().containsValue(task)) {

            projects.get(projectId).getTasks().put(task.getId(), task);
        } else {
            result = "already there";
        }
        return result;
    }

    public String deleteTask(Integer projectId, Integer taskId) {
        result = null;
        if (projects.get(projectId).getTasks().containsKey(taskId)) {
            projects.get(projectId).getTasks().remove(taskId);
        } else {
            result = "no task with this id";
        }
        return result;
    }

    public String updateTask(Integer projectId, Integer taskId, Integer updateId, String newData) {
        String result = null;
        if (projects.containsKey(projectId) && projects.get(projectId).getTasks().containsKey(taskId)) {
            switch (updateId) {
                case 1:
                    projects.get(projectId).getTasks().get(taskId).setName(newData);
                    break;
                case 2:
                    projects.get(projectId).getTasks().get(taskId).setDescription(newData);
                    break;
                case 3:
                    projects.get(projectId).getTasks().get(taskId).setDateFinish(newData);
                    break;
                default:
                    result = "wrong update id";
            }
        } else {
            result = "wrong id";
        }
        return result;
    }

    public String deleteAll(Integer projectId) {
        result = null;
        if (projects.containsKey(projectId)) {
            projects.get(projectId).getTasks().clear();
        } else {
            result = "wrong project id";
        }
        return result;
    }

    public Map<Integer, Task> print(Integer projectId) {
        if (projects.containsKey(projectId)) {
            return projects.get(projectId).getTasks();
        } else {
            return null;
        }

    }

}
