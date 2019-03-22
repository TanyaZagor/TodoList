package ru.zagorodnikova.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.sql.SQLException;
import java.util.List;


public interface ITaskRepository<T extends Task> {

    @Nullable
    T persist(@NotNull final T t) throws Exception;

    void remove(@NotNull final T t) throws Exception;

    void removeAll(@NotNull final T t) throws Exception;

    void removeAllInProject(@NotNull final T t) throws Exception;

    @Nullable
    T findOne(@NotNull final T t);

    void merge(@NotNull final T t) throws Exception;

    @Nullable
    List<T> findAllTasksInProject(@NotNull final T t);

    @Nullable
    List<T> findAllTasks(@NotNull final T t);

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

    void setTasks(@NotNull  final List<T> list);

}
