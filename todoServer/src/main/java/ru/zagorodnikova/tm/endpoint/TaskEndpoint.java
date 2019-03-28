package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.ITaskEndpoint;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.Task;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class TaskEndpoint implements ITaskEndpoint {

    @NotNull private final ServiceLocator serviceLocator;

    public TaskEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public Task persistTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName,
            @WebParam(name = "taskName") @NotNull final String taskName,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().persistTask(session.getUserId(), projectName, taskName, description, dateStart, dateFinish);
    }

    public void removeTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName,
            @WebParam(name = "taskName") @NotNull final String taskName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeTask(session.getUserId(), projectName, taskName);
    }

    public void removeAllTasksInProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeAllTasksInProject(session.getUserId(), projectName);
    }

    public void removeAllTasks(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeAllTasks(session.getUserId());
    }

    public void mergeTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName,
            @WebParam(name = "oldTaskName") @NotNull final String oldTaskName,
            @WebParam(name = "taskName") @NotNull final String taskName,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish,
            @WebParam(name = "status") @NotNull final String status
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().mergeTask(session.getUserId(), projectName, oldTaskName, taskName, description, dateStart, dateFinish, status);
    }

    @Nullable
    public List<Task> findAllTasksInProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAllTasksInProject(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> findAllTasks(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAllTasks(session.getUserId());
    }

    @Nullable
    public Task findOneTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName,
            @WebParam(name = "taskName") @NotNull final String taskName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findOneTask(session.getUserId(), projectName, taskName);
    }

    @Nullable
    public List<Task> sortTasksByDateCreated(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateCreated(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByDateStart(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateStart(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByDateFinish(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateFinish(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByStatus(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByStatus(session.getUserId(), projectName);
    }
}
