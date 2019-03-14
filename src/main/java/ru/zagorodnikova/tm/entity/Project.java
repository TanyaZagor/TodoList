package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class Project extends AbstractEntity {


    @NotNull
    private String id = super.getId();

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


    public Project(@NotNull String userId, @NotNull String name, @NotNull String description, @NotNull Date dateStart, @NotNull Date dateFinish) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    @NotNull
    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description+ ", Date start: " + dateStart + ", Date finish: " + dateFinish + ", Date create: " + dateCreate + ", Status: " + status;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Project project = (Project) o;
        return Objects.equals(userId, project.userId) &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }
}
