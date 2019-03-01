package Entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Project {

    private Integer id;
    private String name;
    private String description;
    private Map<Integer, Task> tasks;

    public Project(Integer id, String name,String description) {
        tasks = new LinkedHashMap<>();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project id: " + id + ", Name: " + name + ", Description: " + description;
    }
}
