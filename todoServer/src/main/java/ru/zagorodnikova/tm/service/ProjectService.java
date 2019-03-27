package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.Date;
import java.util.List;

public class ProjectService implements IProjectService {

    @NotNull private final IProjectRepository<Project> projectRepository;
    @NotNull private final ITaskRepository<Task> taskRepository;

    public ProjectService(@NotNull final IProjectRepository<Project> projectRepository, @NotNull final ITaskRepository<Task> taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Nullable
    public Project persistProject(@NotNull final String userId, @NotNull final String projectName,
                                  @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return null;
        if (projectRepository.findOne(userId, projectName) == null) {
            if (description.isEmpty()) return null;
            if (dateStart.isEmpty()) return null;
            if (dateFinish.isEmpty()) return null;
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            @NotNull final Project newProject = new Project();
            newProject.setName(projectName);
            newProject.setUserId(userId);
            newProject.setDescription(description);
            newProject.setUserId(userId);
            newProject.setDateStart(start);
            newProject.setDateFinish(finish);
            return projectRepository.persist(newProject);
        }
        return null;
    }

    public void removeProject(@NotNull final String userId, @NotNull final String projectName) throws Exception {
        if (projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            projectRepository.remove(project.getId());
            taskRepository.removeAllInProject(project.getId());
        }
    }

    public void removeAllProjects(@NotNull final String userId) throws Exception {
        taskRepository.removeAll(userId);
        projectRepository.removeAll(userId);
    }

    @Nullable
    public List<Project> findAllProjects(@NotNull final String userId) throws Exception {
        return projectRepository.findAll(userId);
    }

    @Nullable
    public Project findOneProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        return projectRepository.findOne(userId, projectName);
    }

    public void mergeProject(@NotNull final String userId,
                             @NotNull final String oldProjectName,
                             @NotNull final String projectName,
                             @NotNull final String description,
                             @NotNull final String dateStart,
                             @NotNull final String dateFinish,
                             @NotNull final String status) throws Exception {
        if (oldProjectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(userId, oldProjectName);
        if (project != null) {
            if (projectName.isEmpty()) return;
            if (description.isEmpty()) return;
            if (dateStart.isEmpty()) return;
            if (dateFinish.isEmpty()) return;
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            projectRepository.merge(project.getId(), projectName, description, start, finish, status);
        }
    }

    @Nullable
    public List<Project> sortProjectsByDateCreated(@NotNull final String userId) {
        return projectRepository.sortByDateCreated(userId);
    }

    @Nullable
    public List<Project> sortProjectsByDateStart(@NotNull final String userId) {
        return projectRepository.sortByDateStart(userId);
    }

    @Nullable
    public List<Project> sortProjectsByDateFinish(@NotNull final String userId) {
        return projectRepository.sortByDateFinish(userId);
    }

    @Nullable
    public List<Project> sortProjectsByStatus(@NotNull final String userId) {
        return projectRepository.sortByStatus(userId);
    }
}
