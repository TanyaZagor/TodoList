package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.ITaskEndpoint;
import ru.zagorodnikova.tm.dto.TaskDto;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.Task;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class TaskEndpoint implements ITaskEndpoint {

    @NotNull private final ServiceLocator serviceLocator;

    public TaskEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public TaskDto persistTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName,
            @WebParam(name = "taskName") @NotNull final String taskName,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @Nullable final Task task = serviceLocator.getTaskService().persistTask(session.getUserId(), projectName, taskName, description, dateStart, dateFinish);
        if (task == null) return null;
        return new TaskDto(task);
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
    public List<TaskDto> findAllTasksInProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().findAllTasksInProject(session.getUserId(), projectName);
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    public List<TaskDto> findAllTasks(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().findAllTasks(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    public TaskDto findOneTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName,
            @WebParam(name = "taskName") @NotNull final String taskName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @Nullable final Task task = serviceLocator.getTaskService().findOneTask(session.getUserId(), projectName, taskName);
        if (task == null) return null;
        return new TaskDto(task);
    }

    @Nullable
    public List<TaskDto> sortTasksByDateCreated(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().sortTasksByDateCreated(session.getUserId(), projectName);
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    public List<TaskDto> sortTasksByDateStart(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().sortTasksByDateStart(session.getUserId(), projectName);
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    public List<TaskDto> sortTasksByDateFinish(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().sortTasksByDateFinish(session.getUserId(), projectName);
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    public List<TaskDto> sortTasksByStatus(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "projectName") @NotNull final String projectName
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().sortTasksByStatus(session.getUserId(), projectName);
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }
}
