package ru.zagorodnikova.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Session;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
public class ProjectEndpoint implements IProjectEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public ProjectEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public Project persistProject(@WebParam(name = "session") @NotNull Session session,
                                  @WebParam(name = "name") @NotNull String name,
                                  @WebParam(name = "description") @NotNull String description,
                                  @WebParam(name = "dateStart") @NotNull String dateStart,
                                  @WebParam(name = "dateFinish") @NotNull String dateFinish) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().persistProject(session.getUserId(), name, description, dateStart, dateFinish);
    }

    public void removeProject(@WebParam(name = "session") @NotNull Session session,
                              @WebParam(name = "name") @NotNull String name) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeProject(session.getUserId(), name);
    }

    public void removeAllProjects(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeAllProjects(session.getUserId());
    }

    @Nullable
    public List<Project> findAllProjects(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findAllProjects(session.getUserId());
    }

    @Nullable
    public Project findOneProject(@WebParam(name = "session") @NotNull Session session,
                                  @WebParam(name = "name") @NotNull String name,
                                  @WebParam(name = "description") @NotNull String description) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findOneProject(session.getUserId(), name, description);
    }

    public void mergeProject(@WebParam(name = "session") @NotNull Session session,
                             @WebParam(name = "oldName") @NotNull String oldName,
                             @WebParam(name = "name") @NotNull String name,
                             @WebParam(name = "description") @NotNull String description,
                             @WebParam(name = "dateStart") @NotNull String dateStart,
                             @WebParam(name = "dateFinish") @NotNull String dateFinish) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().mergeProject(session.getUserId(), oldName, name, description, dateStart, dateFinish);
    }

    @Nullable
    public List<Project> sortProjectsByDateCreated(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateCreated(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByDateStart(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateStart(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByDateFinish(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateFinish(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByStatus(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByStatus(session.getUserId());
    }
}
