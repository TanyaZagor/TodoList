package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ITaskService<T extends AbstractEntity> {

    @WebMethod
    @Nullable
    T persistTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    @WebMethod
    void removeTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName);

    @WebMethod
    void removeAllTasksInProject(@NotNull String userId, @NotNull String projectName);

    @WebMethod
    void removeAllTasks(@NotNull String userId);

    @WebMethod
    void mergeTask(@NotNull String userId, @NotNull String projectName, @NotNull String oldTaskName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish);

    @WebMethod
    @Nullable
    List<T> findAllTasksInProject(@NotNull String userId, @NotNull String projectName);

    @WebMethod
    @Nullable
    List<T> findAllTasks(@NotNull String userId);

    @WebMethod
    @Nullable
    T findOneTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String taskDescription);

    @WebMethod
    @Nullable
    List<T> sortTasksByDateCreated(@NotNull String userId, @NotNull String projectName);

    @WebMethod
    @Nullable
    List<T> sortTasksByDateStart(@NotNull String userId, @NotNull String projectName);

    @WebMethod
    @Nullable
    List<T> sortTasksByDateFinish(@NotNull String userId, @NotNull String projectName);

    @WebMethod
    @Nullable
    List<T> sortTasksByStatus(@NotNull String userId, @NotNull String projectName);

}
