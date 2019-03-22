package ru.zagorodnikova.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.sql.SQLException;
import java.util.List;

public interface IProjectRepository<T extends Project> {

    @Nullable
    T persist(@NotNull final T t) throws Exception;

    void remove(@NotNull final T t) throws Exception;

    void removeAll(@NotNull final T t) throws Exception;

    @Nullable
    T findOne(@NotNull final T t);

    void merge(@NotNull final T t) throws Exception;

    @Nullable
    List<T> findAll(@NotNull final T t) throws Exception;

    @NotNull
    List<T> sortByDateCreated(@NotNull final List<T> list);

    @NotNull
    List<T> sortByDateStart(@NotNull final List<T> list);

    @NotNull
    List<T> sortByDateFinish(@NotNull final List<T> list);

    @NotNull
    List<T> sortByStatus(@NotNull final List<T> list);

    @NotNull
    List<T> getProjects();

    void setProjects(@NotNull final List<T> list);

}
