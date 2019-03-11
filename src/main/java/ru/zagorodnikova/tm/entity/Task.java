package ru.zagorodnikova.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {

    private String id = UUID.randomUUID().toString();
    private String userId;
    private String projectId;
    private String name;
    private String description;
    private String dateStart;
    private String dateFinish;


    public Task(String userId, String projectId, String name, String  description, String dateStart, String dateFinish) {
        this.userId = userId;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Date start: " + dateStart + ", Date finish: " + dateFinish;
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
