package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.zagorodnikova.tm.api.service.ITaskService")
public class TaskEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public TaskEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public AbstractEntity persistTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        return serviceLocator.getTaskService().persistTask(userId, projectName, taskName, description, dateStart, dateFinish);
    }

    public void removeTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName) {
        serviceLocator.getTaskService().removeTask(userId, projectName, taskName);
    }

    public void removeAllTasksInProject(@NotNull String userId, @NotNull String projectName) {
        serviceLocator.getTaskService().removeAllTasksInProject(userId, projectName);
    }

    public void removeAllTasks(@NotNull String userId) {
        serviceLocator.getTaskService().removeAllTasks(userId);
    }

    public void mergeTask(@NotNull String userId, @NotNull String projectName, @NotNull String oldTaskName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        serviceLocator.getTaskService().mergeTask(userId, projectName, oldTaskName, taskName, description, dateStart, dateFinish);
    }

    @Nullable
    public List findAllTasksInProject(@NotNull String userId, @NotNull String projectName) {
        return serviceLocator.getTaskService().findAllTasksInProject(userId, projectName);
    }

    @Nullable
    public List findAllTasks(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllTasks(userId);
    }

    @Nullable
    public AbstractEntity findOneTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String taskDescription) {
        return serviceLocator.getTaskService().findOneTask(userId, projectName, taskName, taskDescription);
    }

    @Nullable
    public List sortTasksByDateCreated(@NotNull String userId, @NotNull String projectName) {
        return serviceLocator.getTaskService().sortTasksByDateCreated(userId, projectName);
    }

    @Nullable
    public List sortTasksByDateStart(@NotNull String userId, @NotNull String projectName) {
        return serviceLocator.getTaskService().sortTasksByDateStart(userId, projectName);
    }

    @Nullable
    public List sortTasksByDateFinish(@NotNull String userId, @NotNull String projectName) {
        return serviceLocator.getTaskService().sortTasksByDateFinish(userId, projectName);
    }

    @Nullable
    public List sortTasksByStatus(@NotNull String userId, @NotNull String projectName) {
        return serviceLocator.getTaskService().sortTasksByStatus(userId, projectName);
    }
}
