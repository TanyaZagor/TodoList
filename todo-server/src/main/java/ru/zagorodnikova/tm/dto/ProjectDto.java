package ru.zagorodnikova.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto {

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateFinish;

    @Nullable
    private Date dateStart;

    @NotNull
    private Date dateCreate;

    @NotNull
    private Status status;

    public ProjectDto(@NotNull final Project project) {
        this.name = project.getName();
        this.description = project.getDescription();
        this.dateCreate = project.getDateCreate();
        this.dateFinish = project.getDateFinish();
        this.dateStart = project.getDateStart();
        this.status = project.getStatus();
    }
}
