package ru.zagorodnikova.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Session;

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
    public Project persistProject(@NotNull Session session, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().persistProject(session.getUserId(), projectName, description, dateStart, dateFinish);
    }

    public void removeProject(@NotNull Session session, @NotNull String projectName) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeProject(session.getUserId(), projectName);
    }

    public void removeAllProjects(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeAllProjects(session.getUserId());
    }

    @Nullable
    public List<Project> findAllProjects(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findAllProjects(session.getUserId());
    }

    @Nullable
    public Project findOneProject(@NotNull Session session, @NotNull String projectName, @NotNull String projectDescription) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findOneProject(session.getUserId(), projectName, projectDescription);
    }

    public void mergeProject(@NotNull Session session, @NotNull String oldProjectName, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().mergeProject(session.getUserId(), oldProjectName, projectName, description, dateStart, dateFinish);
    }

    @Nullable
    public List<Project> sortProjectsByDateCreated(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateCreated(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByDateStart(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateStart(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByDateFinish(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByDateFinish(session.getUserId());
    }

    @Nullable
    public List<Project> sortProjectsByStatus(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().sortProjectsByStatus(session.getUserId());
    }
}
