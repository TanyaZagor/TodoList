package ru.zagorodnikova.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public Task(String userId, @Nullable String projectId, @Nullable String name, @Nullable String  description, @Nullable String dateStart, @Nullable String dateFinish) {
        this.userId = userId;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateFormatter(dateStart);
        this.dateFinish = dateFormatter(dateFinish);
    }

    public Date dateFormatter(String dateFinish) {
        DateFormat dateFormat = new SimpleDateFormat("DD.MM.YYYY");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateFinish);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void setDateStart(@Nullable String dateStart) {
        this.dateStart = dateFormatter(dateStart);
    }

    public void setDateFinish(@Nullable String dateFinish) {
        this.dateFinish = dateFormatter(dateFinish);
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
