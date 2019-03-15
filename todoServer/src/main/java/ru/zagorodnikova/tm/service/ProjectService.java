package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.util.UtilDateFormatter;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

public class ProjectService extends AbstractService implements IProjectService{

    @NotNull private final IProjectRepository<AbstractEntity> projectRepository;
    @NotNull private final ITaskRepository<AbstractEntity> taskRepository;

    public ProjectService(@NotNull IProjectRepository<AbstractEntity> projectRepository, @NotNull ITaskRepository<AbstractEntity> taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Nullable
    public AbstractEntity persistProject(@NotNull String userId, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish){
        if (projectName.isEmpty()) return null;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
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
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            projectRepository.remove(project);
            taskRepository.removeAllInProject(project);
        }
    }

    public void removeAllProjects(@NotNull String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        projectRepository.removeAll(project);
        taskRepository.removeAll(project);
    }

    @Nullable
    public List<AbstractEntity> findAllProjects(@NotNull String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

    @Nullable
    public AbstractEntity findOneProject(@NotNull String userId, @NotNull String projectName, @NotNull String projectDescription) {
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

    public void mergeProject(@NotNull String userId, @NotNull String oldProjectName, @NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish){
        if (oldProjectName.isEmpty()) return;
        @NotNull final Project newProject = new Project();
        newProject.setName(oldProjectName);
        newProject.setUserId(userId);
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
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
    public List<AbstractEntity> sortProjectsByDateCreated(@NotNull String userId) {
        @Nullable final List<AbstractEntity> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByDateCreated(list);
        }
        return null;
    }

    @Nullable
    public List<AbstractEntity> sortProjectsByDateStart(@NotNull String userId) {
        @Nullable final List<AbstractEntity> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByDateStart(list);
        }
        return null;
    }
    @Nullable
    public List<AbstractEntity> sortProjectsByDateFinish(@NotNull String userId) {
        @Nullable final List<AbstractEntity> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByDateFinish(list);
        }
        return null;

    }

    @Nullable
    public List<AbstractEntity> sortProjectsByStatus(@NotNull String userId) {
        @Nullable final List<AbstractEntity> list = findAllProjects(userId);
        if (list != null) {
            return projectRepository.sortByStatus(list);
        }
        return null;
    }


}
