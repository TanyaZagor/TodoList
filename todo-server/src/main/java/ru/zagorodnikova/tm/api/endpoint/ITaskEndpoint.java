package ru.zagorodnikova.tm.api.endpoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.dto.TaskDto;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
public interface ITaskEndpoint {

    @WebMethod
    @Nullable
    TaskDto persistTask(@WebParam(name = "session") @NotNull final Session session,
                        @WebParam(name = "projectName") @NotNull final String projectName,
                        @WebParam(name = "taskName") @NotNull final String taskName,
                        @WebParam(name = "description") @NotNull final String description,
                        @WebParam(name = "dateStart") @NotNull final String dateStart,
                        @WebParam(name = "dateFinish") @NotNull final String dateFinish) throws Exception;

    @WebMethod
    void removeTask(@WebParam(name = "session") @NotNull final Session session,
                    @WebParam(name = "projectName") @NotNull final String projectName,
                    @WebParam(name = "taskName") @NotNull final String taskName) throws Exception;

    @WebMethod
    void removeAllTasksInProject(@WebParam(name = "session") @NotNull final Session session,
                                 @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    void removeAllTasks(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void mergeTask(@WebParam(name = "session") @NotNull final Session session,
                   @WebParam(name = "projectName") @NotNull final String projectName,
                   @WebParam(name = "oldTaskName") @NotNull final String oldTaskName,
                   @WebParam(name = "taskName") @NotNull final String taskName,
                   @WebParam(name = "description") @NotNull final String description,
                   @WebParam(name = "dateStart") @NotNull final String dateStart,
                   @WebParam(name = "dateFinish") @NotNull final String dateFinish,
                   @WebParam(name = "status") @NotNull final String status) throws Exception;

    @WebMethod
    @Nullable
    List<TaskDto> findAllTasksInProject(@WebParam(name = "session") @NotNull final Session session,
                                     @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<TaskDto> findAllTasks(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    TaskDto findOneTask(@WebParam(name = "session") @NotNull final Session session,
                     @WebParam(name = "projectName") @NotNull final String projectName,
                     @WebParam(name = "taskName") @NotNull final String taskName) throws Exception;

    @WebMethod
    @Nullable
    List<TaskDto> sortTasksByDateCreated(@WebParam(name = "session") @NotNull final Session session,
                                      @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<TaskDto> sortTasksByDateStart(@WebParam(name = "session") @NotNull final Session session,
                                    @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<TaskDto> sortTasksByDateFinish(@WebParam(name = "session") @NotNull final Session session,
                                     @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<TaskDto> sortTasksByStatus(@WebParam(name = "session") @NotNull final Session session,
                                 @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

}
