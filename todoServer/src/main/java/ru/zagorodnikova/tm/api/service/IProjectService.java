package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectService {

    @WebMethod
    @Nullable
    Project persistProject(@NotNull String userId,
                           @NotNull String projectName,
                           @NotNull String description,
                           @NotNull String dateStart,
                           @NotNull String dateFinish);

    @WebMethod
    void removeProject(@NotNull String userId,
                       @NotNull String projectName);

    @WebMethod
    void removeAllProjects(@NotNull String userId);

    @WebMethod
    @Nullable
    List<Project> findAllProjects(@NotNull String userId);

    @WebMethod
    @Nullable
    Project findOneProject(@NotNull String userId,
                     @NotNull String projectName,
                     @NotNull String projectDescription);

    @WebMethod
    void mergeProject(@NotNull String userId,
                      @NotNull String oldProjectName,
                      @NotNull String projectName,
                      @NotNull String description,
                      @NotNull String dateStart,
                      @NotNull String dateFinish);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateCreated(@NotNull String userId);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateStart(@NotNull String userId);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateFinish(@NotNull String userId);

    @WebMethod
    @Nullable
    List<Project> sortProjectsByStatus(@NotNull String userId);
}
