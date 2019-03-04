package ru.zagorodnikova.tm.Entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Project {

    private Integer id;
    private String name;
    private String description;
    private String dateFinish;
    private String dateStart;
    private Map<Integer, Task> tasks;

    public Project(Integer id, String name,String description, String dateStart, String dateFinish) {
        tasks = new LinkedHashMap<>();
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public void setTasks(Map<Integer, Task> tasks) {
        this.tasks = tasks;
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

    public Integer getId() {
        return id;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    @Override
    public String toString() {
        return "Project id: " + id + ", Name: " + name + ", Description: " + description + ", Date finish: " + dateFinish;
    }
}
