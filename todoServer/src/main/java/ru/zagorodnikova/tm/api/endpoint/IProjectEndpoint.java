package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndpoint {

    @WebMethod
    @Nullable
    Project persistProject(@NotNull Session session,
                           @NotNull String projectName,
                           @NotNull String description,
                           @NotNull String dateStart,
                           @NotNull String dateFinish);

    @WebMethod
    void removeProject(@NotNull Session session,
                       @NotNull String projectName);

    @WebMethod
    void removeAllProjects(@NotNull Session session);

    @WebMethod
    @Nullable
    List<Project> findAllProjects(@NotNull Session session);

    @WebMethod
    @Nullable
    Project findOneProject(@NotNull Session session,
                     @NotNull String projectName,
                     @NotNull String projectDescription);

    @WebMethod
    void mergeProject(@NotNull Session session,
                      @NotNull String oldProjectName,
                      @NotNull String projectName,
                      @NotNull String description,
                      @NotNull String dateStart,
                      @NotNull String dateFinish);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateCreated(@NotNull Session session);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateStart(@NotNull Session session);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateFinish(@NotNull Session session);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByStatus(@NotNull Session session);
}
