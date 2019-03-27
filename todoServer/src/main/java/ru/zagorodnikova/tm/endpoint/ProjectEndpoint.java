package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.enumeration.Status;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ProjectEndpoint implements IProjectEndpoint {

    @NotNull private final ServiceLocator serviceLocator;

    public ProjectEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public Project persistProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().persistProject(session.getUserId(), name, description, dateStart, dateFinish);
    }

    public void removeProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeProject(session.getUserId(), name);
    }

    public void removeAllProjects(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeAllProjects(session.getUserId());
    }

    @Nullable
    public List<Project> findAllProjects(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findAllProjects(session.getUserId());
    }

    @Nullable
    public Project findOneProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findOneProject(session.getUserId(), name);
    }

    public void mergeProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "oldName") @NotNull final String oldName,
            @WebParam(name = "name") @NotNull final String name,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish,
            @WebParam(name = "status") @NotNull final String status
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().mergeProject(session.getUserId(), oldName, name, description, dateStart, dateFinish, status);
    }

    @Nullable
    public List<Project> sortProjectsByDateCreated(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateCreated(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByDateStart(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateStart(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByDateFinish(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateFinish(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByStatus(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByStatus(session.getUserId());
    }
}
