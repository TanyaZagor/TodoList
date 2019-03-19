package ru.zagorodnikova.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {

    @NotNull
    private String id = super.getId();

    @Nullable
    private String userId;

    @Nullable
    private String projectId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateStart;

    @Nullable
    private Date dateFinish;

    @NotNull
    private Date dateCreate = new Date();

    @NotNull
    private Status status = Status.SCHEDULED;

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name, @NotNull String  description, @NotNull Date dateStart, @NotNull Date dateFinish) {
        this.userId = userId;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    @NotNull
    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Date start: " + dateStart + ", Date finish: " + dateFinish + ", Date create: " + dateCreate + ", Status: " + status;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Task task = (Task) o;
        return projectId.equals(task.projectId) &&
                name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name);
    }
}
