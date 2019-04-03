package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.dto.ProjectDto;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Session;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class ProjectEndpoint implements IProjectEndpoint {

    @Inject
    private ISessionService sessionService;

    @Inject
    private IProjectService projectService;

    @Nullable
    public ProjectDto persistProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name,
            @WebParam(name = "description") @NotNull final String description,
            @WebParam(name = "dateStart") @NotNull final String dateStart,
            @WebParam(name = "dateFinish") @NotNull final String dateFinish
    ) throws Exception {
        sessionService.validate(session);
        @Nullable final Project project = projectService.persistProject(session.getUserId(), name, description, dateStart, dateFinish);
        if (project == null) return null;
        return new ProjectDto(project);
    }

    public void removeProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name
    ) throws Exception {
        sessionService.validate(session);
        projectService.removeProject(session.getUserId(), name);
    }

    public void removeAllProjects(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.validate(session);
        projectService.removeAllProjects(session.getUserId());
    }

    @Nullable
    public List<ProjectDto> findAllProjects(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.findAllProjects(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @Nullable
    public ProjectDto findOneProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "name") @NotNull final String name
    ) throws Exception {
        sessionService.validate(session);
        @Nullable final Project project = projectService.findOneProject(session.getUserId(), name);
        if (project == null) return null;
        return new ProjectDto(project);
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
        sessionService.validate(session);
        projectService.mergeProject(session.getUserId(), oldName, name, description, dateStart, dateFinish, status);
    }

    @Nullable
    public List<ProjectDto> sortProjectsByDateCreated(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.sortProjectsByDateCreated(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @Nullable
    public List<ProjectDto> sortProjectsByDateStart(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.sortProjectsByDateStart(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @Nullable
    public List<ProjectDto> sortProjectsByDateFinish(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.sortProjectsByDateFinish(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @Nullable
    public List<ProjectDto> sortProjectsByStatus(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.sortProjectsByStatus(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }
}
