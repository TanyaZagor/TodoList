package ru.zagorodnikova.tm.Entity;


import java.util.Objects;
import java.util.UUID;

public class Task {

    private String id = UUID.randomUUID().toString();
    private String projectId;
    private String name;
    private String description;
    private String dateStart;
    private String dateFinish;

    public Task(String projectId, String name, String  description, String dateStart, String dateFinish) {
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public String getDateFinish() {
        return dateFinish;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " Task id: " + id + ", Name: " + name + ", Description: " + description + ", Date start: " + dateStart + ", Date finish: " + dateFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return projectId.equals(task.projectId) &&
                name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name);
    }
}
