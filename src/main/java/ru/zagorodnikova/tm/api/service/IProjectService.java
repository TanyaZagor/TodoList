package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectService<T extends Project> {

    @Nullable
    T persist(@NotNull String userId, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    void remove(@NotNull String userId, @NotNull String projectName);

    void removeAll(@NotNull String userId);

    @Nullable
    List<T> findAll(@NotNull String userId);

    @Nullable
    T findOne(@NotNull String userId, @NotNull String projectName, @NotNull String projectDescription);

    void merge(@NotNull String userId, @NotNull String oldProjectName, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    @Nullable
    List<T> sortByDateCreated(@NotNull String userId);

    @Nullable
    List<T> sortByDateStart(@NotNull String userId);

    @Nullable
    List<T> sortByDateFinish(@NotNull String userId);

    @Nullable
    List<T> sortByStatus(@NotNull String userId);
}
