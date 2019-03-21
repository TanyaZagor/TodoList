package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.util.UtilDateFormatter;

import java.util.Date;
import java.util.List;

public class ProjectService implements IProjectService {

    @NotNull private final IProjectRepository<Project> projectRepository;
    @NotNull private final ITaskRepository<Task> taskRepository;

    public ProjectService(@NotNull IProjectRepository<Project> projectRepository, @NotNull ITaskRepository<Task> taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Nullable
    public Project persistProject(@NotNull String userId, @NotNull String projectName,
                                  @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish){
        if (projectName.isEmpty()) return null;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = projectRepository.findOne(newProject);
        if (project == null) {
            if (description.isEmpty()) return null;
            if (dateStart.isEmpty()) return null;
            if (dateFinish.isEmpty()) return null;
            @NotNull final Date start = UtilDateFormatter.dateFormatter(dateStart);
            @NotNull final Date finish = UtilDateFormatter.dateFormatter(dateFinish);
            newProject.setDescription(description);
            newProject.setUserId(userId);
            newProject.setDateStart(start);
            newProject.setDateFinish(finish);
            return projectRepository.persist(newProject);
        }
        return null;
    }

    public void removeProject(@NotNull String userId, @NotNull String projectName){
        if (projectName.isEmpty()) return;
        @NotNull final Project newProject= new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = projectRepository.findOne(newProject);
        if (project != null) {
            projectRepository.remove(project);
            Task task = new Task();
            task.setProjectId(project.getId());
            taskRepository.removeAllInProject(task);
        }
    }

    public void removeAllProjects(@NotNull String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        projectRepository.removeAll(project);
        Task task = new Task();
        task.setProjectId(project.getId());
        taskRepository.removeAll(task);
    }

    @Nullable
    public List<Project> findAllProjects(@NotNull String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

    @Nullable
    public Project findOneProject(@NotNull String userId, @NotNull String projectName,
                                  @NotNull String projectDescription) {
        if (projectName.isEmpty()) {
            if (projectDescription.isEmpty()) return null;
            @NotNull final Project newProject = new Project();
            newProject.setDescription(projectDescription);
            newProject.setUserId(userId);
            return projectRepository.findOne(newProject);
        }
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        return projectRepository.findOne(newProject);
    }

    public void mergeProject(@NotNull String userId, @NotNull String oldProjectName, @NotNull String projectName,
                             @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish){
        if (oldProjectName.isEmpty()) return;
        @NotNull final Project newProject = new Project();
        newProject.setName(oldProjectName);
        newProject.setUserId(userId);
        @Nullable final Project project = projectRepository.findOne(newProject);
        if (project != null) {
            if (projectName.isEmpty()) return;
            if (description.isEmpty()) return;
            if (dateStart.isEmpty()) return;
            if (dateFinish.isEmpty()) return;
            @NotNull final Date start = UtilDateFormatter.dateFormatter(dateStart);
            @NotNull final Date finish = UtilDateFormatter.dateFormatter(dateFinish);
            newProject.setId(project.getId());
            newProject.setName(projectName);
            newProject.setDescription(description);
            newProject.setDateStart(start);
            newProject.setDateFinish(finish);
            projectRepository.merge(newProject);
        }
    }

    @Nullable
    public List<Project> sortProjectsByDateCreated(@NotNull String userId) {
        @Nullable final List<Project> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByDateCreated(list);
        }
        return null;
    }

    @Nullable
    public List<Project> sortProjectsByDateStart(@NotNull String userId) {
        @Nullable final List<Project> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByDateStart(list);
        }
        return null;
    }
    @Nullable
    public List<Project> sortProjectsByDateFinish(@NotNull String userId) {
        @Nullable final List<Project> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByDateFinish(list);
        }
        return null;

    }

    @Nullable
    public List<Project> sortProjectsByStatus(@NotNull String userId) {
        @Nullable final List<Project> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByStatus(list);
        }
        return null;
    }
}
