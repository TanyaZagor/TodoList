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
public class Project extends AbstractEntity {

    private String id = UUID.randomUUID().toString();

    @Nullable
    private String userId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateFinish;

    @Nullable
    private Date dateStart;

    @NotNull
    private Date dateCreate = new Date();

    @NotNull
    private Status status = Status.SCHEDULED;


    public Project(@Nullable String userId, @Nullable String name, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish) {
        this.userId = userId;
        this.name = name;
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
        return "Name: " + name + ", Description: " + description+ ", Date start: " + dateStart + ", Date finish: " + dateFinish + ", Date create: " + dateCreate + ", Status: " + status;
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
