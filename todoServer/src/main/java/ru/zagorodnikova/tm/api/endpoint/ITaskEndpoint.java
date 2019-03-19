package ru.zagorodnikova.tm.api.endpoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    Task persistTask(@NotNull String userId,
                     @NotNull String projectName,
                     @NotNull String taskName,
                     @NotNull String description,
                     @NotNull String dateStart,
                     @NotNull String dateFinish);

    @WebMethod
    void removeTask(@NotNull String userId,
                    @NotNull String projectName,
                    @NotNull String taskName);

    @WebMethod
    void removeAllTasksInProject(@NotNull String userId,
                                 @NotNull String projectName);

    @WebMethod
    void removeAllTasks(@NotNull String userId);

    @WebMethod
    void mergeTask(@NotNull String userId,
                   @NotNull String projectName,
                   @NotNull String oldTaskName,
                   @NotNull String taskName,
                   @NotNull String description,
                   @NotNull String dateStart,
                   @NotNull String dateFinish);

    @WebMethod
    @Nullable
    List<Task> findAllTasksInProject(@NotNull String userId,
                                       @NotNull String projectName);

    @WebMethod
    @Nullable
    List<Task> findAllTasks(@NotNull String userId);

    @WebMethod
    @Nullable
    Task findOneTask(@NotNull String userId,
                  @NotNull String projectName,
                  @NotNull String taskName,
                  @NotNull String description);

    @WebMethod
    @Nullable
    List<Task> sortTasksByDateCreated(@NotNull String userId,
                                   @NotNull String projectName);

    @WebMethod
    @Nullable
    List<Task> sortTasksByDateStart(@NotNull String userId,
                                 @NotNull String projectName);

    @WebMethod
    @Nullable
    List<Task> sortTasksByDateFinish(@NotNull String userId,
                                  @NotNull String projectName);

    @WebMethod
    @Nullable
    List<Task> sortTasksByStatus(@NotNull String userId,
                              @NotNull String projectName);

}
