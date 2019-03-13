package ru.zagorodnikova.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.util.UtilDateFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {

    private String id = UUID.randomUUID().toString();

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

    public Task(String userId, @Nullable String projectId, @Nullable String name, @Nullable String  description, @Nullable Date dateStart, @Nullable Date dateFinish) {
        this.userId = userId;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }


    public void setDateStart(@Nullable Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateFinish(@Nullable Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Date start: " + dateStart + ", Date finish: " + dateFinish + ", Date create: " + dateCreate + ", Status: " + status;
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
