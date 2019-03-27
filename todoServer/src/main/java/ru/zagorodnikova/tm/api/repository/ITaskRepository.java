package ru.zagorodnikova.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface ITaskRepository<T extends Task> {

    @Nullable
    T persist(@NotNull final T t) throws Exception;

    void remove(@NotNull final String id) throws Exception;

    void removeAll(@NotNull final String userId) throws Exception;

    void removeAllInProject(@NotNull final String projectId) throws Exception;

    @Nullable
    T findOne(@NotNull final String projectId, @NotNull final String name);

    void merge(@NotNull final String id,
               @NotNull final String name,
               @NotNull final String description,
               @NotNull final Date dateStart,
               @NotNull final Date dateFinish,
               @NotNull final String status) throws Exception;

    @Nullable
    List<T> findAllTasksInProject(@NotNull final String projectId);

    @Nullable
    List<T> findAllTasks(@NotNull final String userId);

    @NotNull
    List<T> sortByDateCreated(@NotNull final List<T> list);

    @NotNull
    List<Task> sortByDateStart(@NotNull final List<T> list);

    @NotNull
    List<T> sortByDateFinish(@NotNull final List<T> list);

    @NotNull
    List<T> sortByStatus(@NotNull final List<T> list);

    @NotNull
    List<T> getTasks();

    void setTasks(@NotNull  final List<T> list) throws Exception;

}
