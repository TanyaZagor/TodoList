package ru.zagorodnikova.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.enumeration.Status;

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

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name,
                @NotNull String  description, @NotNull Date dateStart, @NotNull Date dateFinish) {
        this.userId = userId;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public void setStatusString(@NotNull String status) {
        switch (status) {
            case "scheduled": this.status = Status.SCHEDULED; break;
            case "in progress": this.status = Status.IN_PROGRESS; break;
            case "done" : this.status = Status.DONE; break;
            default: this.status = Status.SCHEDULED; break;
        }
    }

    public String getStatusString() {
        return status.toString();
    }


    public void setStatus(@NotNull Status status) {
        this.status = status;
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
