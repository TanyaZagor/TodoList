package ru.zagorodnikova.tm.api.endpoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    Task persistTask(@WebParam(name = "session") @NotNull final Session session,
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
                   @WebParam(name = "dateFinish") @NotNull final String dateFinish) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasksInProject(@WebParam(name = "session") @NotNull final Session session,
                                     @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasks(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    Task findOneTask(@WebParam(name = "session") @NotNull final Session session,
                     @WebParam(name = "projectName") @NotNull final String projectName,
                     @WebParam(name = "taskName") @NotNull final String taskName,
                     @WebParam(name = "description") @NotNull final String description) throws Exception;

    @WebMethod
    @Nullable
    List<Task> sortTasksByDateCreated(@WebParam(name = "session") @NotNull final Session session,
                                      @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<Task> sortTasksByDateStart(@WebParam(name = "session") @NotNull final Session session,
                                    @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<Task> sortTasksByDateFinish(@WebParam(name = "session") @NotNull final Session session,
                                     @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

    @WebMethod
    @Nullable
    List<Task> sortTasksByStatus(@WebParam(name = "session") @NotNull final Session session,
                                 @WebParam(name = "projectName") @NotNull final String projectName) throws Exception;

}
