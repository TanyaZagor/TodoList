package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import java.util.List;

public interface ITaskService<T extends AbstractEntity> {

    @Nullable
    T persist(@NotNull String userId, @Nullable String projectName, @Nullable String taskName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish);

    void remove(@NotNull String userId, @Nullable String projectName, @Nullable String taskName);

    void removeAllInProject(@NotNull String userId, @Nullable String projectName);

    void removeAll(@NotNull String userId);

    void merge(@NotNull String userId, @Nullable String projectName, @Nullable String oldTaskName, @Nullable String taskName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish);

    @Nullable
    List<T> findAll(@NotNull String userId, @Nullable String projectName);

    @Nullable
    T findOne(@NotNull String userId, @Nullable String projectName, @Nullable String taskName, @Nullable String taskDescription);

    @Nullable
    List<T> sortByDateCreated(@NotNull String userId, @Nullable String projectName);

    @Nullable
    List<T> sortByDateStart(@NotNull String userId, @Nullable String projectName);

    @Nullable
    List<T> sortByDateFinish(@NotNull String userId, @Nullable String projectName);

    @Nullable
    List<T> sortByStatus(@NotNull String userId, @Nullable String projectName);
}
