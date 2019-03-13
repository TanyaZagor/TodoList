package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectService<T extends AbstractEntity> {

    @Nullable
    T persist(@NotNull String userId, @Nullable String projectName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish);

    void remove(@NotNull String userId, @Nullable String projectName);

    void removeAll(@NotNull String userId);

    @Nullable
    List<T> findAll(@NotNull String userId);

    @Nullable
    T findOne(@NotNull String userId, @Nullable String projectName);

    void merge(@NotNull String userId, @Nullable String oldProjectName, @Nullable String projectName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish);

    @Nullable
    List<T> sortByDateCreated(@NotNull String userId);

    @Nullable
    List<T> sortByDateStart(@NotNull String userId);

    @Nullable
    List<T> sortByDateFinish(@NotNull String userId);
}
