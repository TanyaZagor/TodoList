package ru.zagorodnikova.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.entity.Project;

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
    public Project persistProject(@NotNull String userId, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        return serviceLocator.getProjectService().persistProject(userId, projectName, description, dateStart, dateFinish);
    }

    public void removeProject(@NotNull String userId, @NotNull String projectName) {
        serviceLocator.getProjectService().removeProject(userId, projectName);
    }

    public void removeAllProjects(@NotNull String userId) {
        serviceLocator.getProjectService().removeAllProjects(userId);
    }

    @Nullable
    public List<Project> findAllProjects(@NotNull String userId) {
        return serviceLocator.getProjectService().findAllProjects(userId);
    }

    @Nullable
    public Project findOneProject(@NotNull String userId, @NotNull String projectName, @NotNull String projectDescription) {
        return serviceLocator.getProjectService().findOneProject(userId, projectName, projectDescription);
    }

    public void mergeProject(@NotNull String userId, @NotNull String oldProjectName, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        serviceLocator.getProjectService().mergeProject(userId, oldProjectName, projectName, description, dateStart, dateFinish);
    }

    @Nullable
    public List<Project> sortProjectsByDateCreated(@NotNull String userId) {
        return serviceLocator.getProjectService().sortProjectsByDateCreated(userId);
    }

    @Nullable
    public List<Project> sortProjectsByDateStart(@NotNull String userId) {
        return serviceLocator.getProjectService().sortProjectsByDateStart(userId);
    }

    @Nullable
    public List<Project> sortProjectsByDateFinish(@NotNull String userId) {
        return serviceLocator.getProjectService().sortProjectsByDateFinish(userId);
    }

    @Nullable
    public List<Project> sortProjectsByStatus(@NotNull String userId) {
        return serviceLocator.getProjectService().sortProjectsByStatus(userId);
    }
}
