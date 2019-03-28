package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

public interface ITaskRepository {

    void persist(@NotNull final Task task);

    void merge(@NotNull final Task task);

    void remove(@NotNull Task task);

    void removeAll(@NotNull final String userId);

    void removeAllInProject(@NotNull final String projectId);

    @Nullable
    Task findOne(@NotNull final String projectId, @NotNull final String name);

    @Nullable
    List<Task> findAllTasksInProject(@NotNull final String projectId);

    @Nullable
    List<Task> findAllTasks(@NotNull final String userId);

    @NotNull
    List<Task> getTasks();

}
