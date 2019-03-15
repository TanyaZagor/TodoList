package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IProjectService<T extends AbstractEntity> {

    @WebMethod
    @Nullable
    T persistProject(@NotNull @WebParam(name = "userId") String userId,
                     @NotNull @WebParam(name = "projectName") String projectName,
                     @NotNull @WebParam(name = "description") String description,
                     @NotNull @WebParam(name = "dateStart") String dateStart,
                     @NotNull @WebParam(name = "dateFinish") String dateFinish);

    @WebMethod
    void removeProject(@NotNull @WebParam(name = "userId") String userId,
                       @NotNull @WebParam(name = "projectName") String projectName);

    @WebMethod
    void removeAllProjects(@NotNull @WebParam(name = "userId") String userId);

    @WebMethod
    @Nullable
    List<T> findAllProjects(@NotNull @WebParam(name = "userId") String userId);

    @WebMethod
    @Nullable
    T findOneProject(@NotNull @WebParam(name = "userId") String userId,
                     @NotNull @WebParam(name = "projectName") String projectName,
                     @NotNull @WebParam(name = "projectDescription") String projectDescription);

    @WebMethod
    void mergeProject(@NotNull @WebParam(name = "userId") String userId,
                      @NotNull @WebParam(name = "oldProjectName") String oldProjectName,
                      @NotNull @WebParam(name = "projectName") String projectName,
                      @NotNull @WebParam(name = "description") String description,
                      @NotNull @WebParam(name = "dateStart") String dateStart,
                      @NotNull @WebParam(name = "dateFinish")String dateFinish);

    @WebMethod
    @Nullable
    List<T> sortProjectsByDateCreated(@NotNull @WebParam(name = "userId") String userId);

    @WebMethod
    @Nullable
    List<T> sortProjectsByDateStart(@NotNull @WebParam(name = "userId") String userId);

    @WebMethod
    @Nullable
    List<T> sortProjectsByDateFinish(@NotNull @WebParam(name = "userId") String userId);

    @WebMethod
    @Nullable
    List<T> sortProjectsByStatus(@NotNull @WebParam(name = "userId") String userId);
}
