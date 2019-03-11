package ru.zagorodnikova.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractEntity {

    private String id = UUID.randomUUID().toString();
    private String userId;
    private String name;
    private String description;
    private String dateFinish;
    private String dateStart;


    public Project(String userId, String name,String description, String dateStart, String dateFinish) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description+ ", Date start: " + dateStart + ", Date finish: " + dateFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(userId, project.userId) &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }
}
