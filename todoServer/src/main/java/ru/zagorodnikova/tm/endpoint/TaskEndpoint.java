package ru.zagorodnikova.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.ITaskEndpoint;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.Task;

import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
public class TaskEndpoint implements ITaskEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public TaskEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public Task persistTask(@NotNull Session session, @NotNull String projectName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().persistTask(session.getUserId(), projectName, taskName, description, dateStart, dateFinish);
    }

    public void removeTask(@NotNull Session session, @NotNull String projectName, @NotNull String taskName) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeTask(session.getUserId(), projectName, taskName);
    }

    public void removeAllTasksInProject(@NotNull Session session, @NotNull String projectName) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeAllTasksInProject(session.getUserId(), projectName);
    }

    public void removeAllTasks(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeAllTasks(session.getUserId());
    }

    public void mergeTask(@NotNull Session session, @NotNull String projectName, @NotNull String oldTaskName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().mergeTask(session.getUserId(), projectName, oldTaskName, taskName, description, dateStart, dateFinish);
    }

    @Nullable
    public List<Task> findAllTasksInProject(@NotNull Session session, @NotNull String projectName) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAllTasksInProject(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> findAllTasks(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAllTasks(session.getUserId());
    }

    @Nullable
    public Task findOneTask(@NotNull Session session, @NotNull String projectName, @NotNull String taskName, @NotNull String taskDescription) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findOneTask(session.getUserId(), projectName, taskName, taskDescription);
    }

    @Nullable
    public List<Task> sortTasksByDateCreated(@NotNull Session session, @NotNull String projectName) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateCreated(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByDateStart(@NotNull Session session, @NotNull String projectName) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateStart(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByDateFinish(@NotNull Session session, @NotNull String projectName) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateFinish(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByStatus(@NotNull Session session, @NotNull String projectName) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByStatus(session.getUserId(), projectName);
    }
}
