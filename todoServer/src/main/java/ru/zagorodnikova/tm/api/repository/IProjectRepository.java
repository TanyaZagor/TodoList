package ru.zagorodnikova.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.util.Date;
import java.util.List;

public interface IProjectRepository<T extends Project> {

    @Nullable
    T persist(@NotNull final T t) throws Exception;

    void remove(@NotNull final String id) throws Exception;

    void removeAll(@NotNull final String userId) throws Exception;

    @Nullable
    T findOne(@NotNull final String userId, @NotNull final String name);

    void merge(
            @NotNull final String id,
            @NotNull final String projectName,
            @NotNull final String description,
            @NotNull final Date dateStart,
            @NotNull final Date dateFinish,
            @NotNull final String status) throws Exception;

    @Nullable
    List<T> findAll(@NotNull final String userId) throws Exception;

    @Nullable
    List<T> sortByDateCreated(@NotNull final String userId);

    @Nullable
    List<T> sortByDateStart(@NotNull final String userId);

    @Nullable
    List<T> sortByDateFinish(@NotNull final String userId);

    @Nullable
    List<T> sortByStatus(@NotNull final String userId);

    @NotNull
    List<T> getProjects();

    void setProjects(@NotNull final List<T> list);

}
