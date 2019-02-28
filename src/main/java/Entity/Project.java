package Entity;

import Repository.TaskRepository;

public class Project {

    private String name;
    private TaskRepository tasks;

    public Project(String name, TaskRepository tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public TaskRepository getTasks() {
        return tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(TaskRepository tasks) {
        this.tasks = tasks;
    }

}
