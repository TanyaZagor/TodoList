package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import java.util.List;

public interface ITaskService<T extends AbstractEntity> {

    @Nullable
    T persist(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    void remove(@NotNull String userId, @NotNull String projectName, @NotNull String taskName);

    void removeAllInProject(@NotNull String userId, @NotNull String projectName);

    void removeAll(@NotNull String userId);

    void merge(@NotNull String userId, @NotNull String projectName, @NotNull String oldTaskName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    @Nullable
    List<T> findAll(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<T> findAllTasks(@NotNull String userId);

    @Nullable
    T findOne(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String taskDescription);

    @Nullable
    List<T> sortByDateCreated(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<T> sortByDateStart(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<T> sortByDateFinish(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<T> sortByStatus(@NotNull String userId, @NotNull String projectName);

}
