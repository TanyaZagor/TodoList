package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndpoint {

    @WebMethod
    @Nullable
    Project persistProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish
    ) throws Exception;

    @WebMethod
    void removeProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name
    ) throws Exception;

    @WebMethod
    void removeAllProjects(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    List<Project> findAllProjects(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    Project findOneProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name) throws Exception;

    @WebMethod
    void mergeProject(@WebParam(name = "session") @NotNull final Session session,
                      @WebParam(name = "oldName") @NotNull final String oldName,
                      @WebParam(name = "name") @NotNull final String name,
                      @WebParam(name = "description") @NotNull final String description,
                      @WebParam(name = "dateStart") @NotNull final String dateStart,
                      @WebParam(name = "dateFinish") @NotNull final String dateFinish,
                      @WebParam(name = "status") @NotNull final String status) throws Exception;

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateCreated(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateStart(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    List<Project> sortProjectsByDateFinish(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    List<Project> sortProjectsByStatus(@WebParam(name = "session") @NotNull final Session session) throws Exception;
}
