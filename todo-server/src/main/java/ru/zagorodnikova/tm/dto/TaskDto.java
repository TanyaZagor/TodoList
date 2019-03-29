package ru.zagorodnikova.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateStart;

    @Nullable
    private Date dateFinish;

    @NotNull
    private Date dateCreate;

    @NotNull
    private Status status;

    public TaskDto(@NotNull final Task task) {
        this.name = task.getName();
        this.description = task.getDescription();
        this.dateCreate = task.getDateCreate();
        this.dateFinish = task.getDateFinish();
        this.dateStart = task.getDateStart();
        this.status = task.getStatus();
    }
}
