package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

public interface ITaskService {

    @Nullable
    Task persistTask(@NotNull final String userId,
                     @NotNull final String projectName,
                     @NotNull final String taskName,
                     @NotNull final String description,
                     @NotNull final String dateStart,
                     @NotNull final String dateFinish) throws Exception;

    void removeTask(@NotNull final String userId,
                    @NotNull final String projectName,
                    @NotNull final String taskName) throws Exception;

    void removeAllTasksInProject(@NotNull final String userId,
                                 @NotNull final String projectName) throws Exception;

    void removeAllTasks(@NotNull final String userId) throws Exception;

    void mergeTask(@NotNull final String userId,
                   @NotNull final String projectName,
                   @NotNull final String oldTaskName,
                   @NotNull final String taskName,
                   @NotNull final String description,
                   @NotNull final String dateStart,
                   @NotNull final String dateFinish) throws Exception;

    @Nullable
    List<Task> findAllTasksInProject(@NotNull final String userId,
                                     @NotNull final String projectName);

    @Nullable
    List<Task> findAllTasks(@NotNull final String userId);

    @Nullable
    Task findOneTask(@NotNull final String userId,
                     @NotNull final String projectName,
                     @NotNull final String taskName);

    @Nullable
    List<Task> sortTasksByDateCreated(@NotNull final String userId,
                                      @NotNull final String projectName);

    @Nullable
    List<Task> sortTasksByDateStart(@NotNull final String userId,
                                    @NotNull final String projectName);

    @Nullable
    List<Task> sortTasksByDateFinish(@NotNull final String userId,
                                     @NotNull final String projectName);

    @Nullable
    List<Task> sortTasksByStatus(@NotNull final String userId,
                                 @NotNull final String projectName);

}
