package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface IProjectService<T extends Project> {

    @WebMethod
    @Nullable
    T persistProject(@NotNull String userId,
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
    List<T> findAllProjects(@NotNull String userId);

    @WebMethod
    @Nullable
    T findOneProject(@NotNull String userId,
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
    List<T> sortProjectsByDateCreated(@NotNull String userId);

    @WebMethod
    @Nullable
    List<T> sortProjectsByDateStart(@NotNull String userId);

    @WebMethod
    @Nullable
    List<T> sortProjectsByDateFinish(@NotNull String userId);

    @WebMethod
    @Nullable
    List<T> sortProjectsByStatus(@NotNull String userId);
}
