package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

public interface ITaskService {

    @Nullable
    Task persistTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    void removeTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName);

    void removeAllTasksInProject(@NotNull String userId, @NotNull String projectName);

    void removeAllTasks(@NotNull String userId);

    void mergeTask(@NotNull String userId, @NotNull String projectName, @NotNull String oldTaskName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    @Nullable
    List<Task> findAllTasksInProject(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<Task> findAllTasks(@NotNull String userId);

    @Nullable
    Task findOneTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String taskDescription);

    @Nullable
    List<Task> sortTasksByDateCreated(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<Task> sortTasksByDateStart(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<Task> sortTasksByDateFinish(@NotNull String userId, @NotNull String projectName);

    @Nullable
    List<Task> sortTasksByStatus(@NotNull String userId, @NotNull String projectName);

}
