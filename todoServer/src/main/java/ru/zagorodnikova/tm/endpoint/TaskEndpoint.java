package ru.zagorodnikova.tm.endpoint;

import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class TaskEndpoint implements ITaskEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public TaskEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public Task persistTask(@WebParam(name = "session") @NotNull Session session,
                            @WebParam(name = "projectName") @NotNull String projectName,
                            @WebParam(name = "taskName") @NotNull String taskName,
                            @WebParam(name = "description") @NotNull String description,
                            @WebParam(name = "dateStart") @NotNull String dateStart,
                            @WebParam(name = "dateFinish") @NotNull String dateFinish) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().persistTask(session.getUserId(), projectName, taskName, description, dateStart, dateFinish);
    }

    public void removeTask(@WebParam(name = "session") @NotNull Session session,
                           @WebParam(name = "projectName") @NotNull String projectName,
                           @WebParam(name = "taskName") @NotNull String taskName) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeTask(session.getUserId(), projectName, taskName);
    }

    public void removeAllTasksInProject(@WebParam(name = "session") @NotNull Session session,
                                        @WebParam(name = "projectName") @NotNull String projectName) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeAllTasksInProject(session.getUserId(), projectName);
    }

    public void removeAllTasks(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeAllTasks(session.getUserId());
    }

    public void mergeTask(@WebParam(name = "session") @NotNull Session session,
                          @WebParam(name = "projectName") @NotNull String projectName,
                          @WebParam(name = "oldTaskName") @NotNull String oldTaskName,
                          @WebParam(name = "taskName") @NotNull String taskName,
                          @WebParam(name = "description") @NotNull String description,
                          @WebParam(name = "dateStart") @NotNull String dateStart,
                          @WebParam(name = "dateFinish") @NotNull String dateFinish) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().mergeTask(session.getUserId(), projectName, oldTaskName, taskName, description, dateStart, dateFinish);
    }

    @Nullable
    public List<Task> findAllTasksInProject(@WebParam(name = "session") @NotNull Session session,
                                            @WebParam(name = "projectName") @NotNull String projectName) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAllTasksInProject(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> findAllTasks(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAllTasks(session.getUserId());
    }

    @Nullable
    public Task findOneTask(@WebParam(name = "session") @NotNull Session session,
                            @WebParam(name = "projectName") @NotNull String projectName,
                            @WebParam(name = "taskName") @NotNull String taskName,
                            @WebParam(name = "description") @NotNull String description) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findOneTask(session.getUserId(), projectName, taskName, description);
    }

    @Nullable
    public List<Task> sortTasksByDateCreated(@WebParam(name = "session") @NotNull Session session,
                                             @WebParam(name = "projectName") @NotNull String projectName) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateCreated(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByDateStart(@WebParam(name = "session") @NotNull Session session,
                                           @WebParam(name = "projectName") @NotNull String projectName) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateStart(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByDateFinish(@WebParam(name = "session") @NotNull Session session,
                                            @WebParam(name = "projectName") @NotNull String projectName) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByDateFinish(session.getUserId(), projectName);
    }

    @Nullable
    public List<Task> sortTasksByStatus(@WebParam(name = "session") @NotNull Session session,
                                        @WebParam(name = "projectName") @NotNull String projectName) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().sortTasksByStatus(session.getUserId(), projectName);
    }
}
