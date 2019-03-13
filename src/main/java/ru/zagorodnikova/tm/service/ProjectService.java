package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.util.UtilDateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectService extends AbstractService implements IProjectService{

    private final IProjectRepository<AbstractEntity> projectRepository;
    private final ITaskRepository<AbstractEntity> taskRepository;

    public ProjectService(@NotNull IProjectRepository<AbstractEntity> projectRepository, @NotNull ITaskRepository<AbstractEntity> taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Nullable
    public AbstractEntity persist(@NotNull String userId, @Nullable String projectName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish){
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project == null) {
            if (description == null || description.isEmpty()) return null;
            if (dateStart == null || dateStart.isEmpty()) return null;
            if (dateFinish == null || dateFinish.isEmpty()) return null;
            Date start = UtilDateFormatter.dateFormatter(dateStart);
            Date finish = UtilDateFormatter.dateFormatter(dateFinish);
            newProject.setDescription(description);
            newProject.setUserId(userId);
            newProject.setDateStart(start);
            newProject.setDateFinish(finish);
            return projectRepository.persist(newProject);
        }
        return null;
    }

    public void remove(@NotNull String userId, @Nullable String projectName){
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject= new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        final Project project = (Project) projectRepository.findOne(newProject);
        projectRepository.remove(project);
        taskRepository.removeAllInProject(project);
    }

    public void removeAll(@NotNull String userId) {
        Project project = new Project();
        project.setUserId(userId);
        projectRepository.removeAll(project);
        taskRepository.removeAll(project);
    }

    @Nullable
    public List<AbstractEntity> findAll(@NotNull String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

    @Nullable
    public AbstractEntity findOne(@NotNull String userId, @Nullable String projectName, @Nullable String projectDescription) {
        if (projectName == null || projectName.isEmpty()) {
            if (projectDescription == null || projectDescription.isEmpty()) return null;
            Project newProject = new Project();
            newProject.setDescription(projectDescription);
            newProject.setUserId(userId);
            return projectRepository.findOne(newProject);
        }
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        return projectRepository.findOne(newProject);
    }

    public void merge(@NotNull String userId, @Nullable String oldProjectName, @Nullable String projectName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish){
        if (oldProjectName == null || oldProjectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(oldProjectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (projectName == null || projectName.isEmpty()) return;
        if (description == null || description.isEmpty()) return;
        if (dateStart == null || dateStart.isEmpty()) return;
        if (dateFinish == null || dateFinish.isEmpty()) return;
        Date start = UtilDateFormatter.dateFormatter(dateStart);
        Date finish = UtilDateFormatter.dateFormatter(dateFinish);
        newProject.setId(project.getId());
        newProject.setName(projectName);
        newProject.setDescription(description);
        newProject.setDateStart(start);
        newProject.setDateFinish(finish);
        projectRepository.merge(newProject);
    }

    @Nullable
    public List<AbstractEntity> sortByDateCreated(@NotNull String userId) {
        return projectRepository.sortByDateCreated(findAll(userId));
    }

    @Nullable
    public List<AbstractEntity> sortByDateStart(@NotNull String userId) {
        return projectRepository.sortByDateStart(findAll(userId));
    }
    @Nullable
    public List<AbstractEntity> sortByDateFinish(@NotNull String userId) {
        return projectRepository.sortByDateFinish(findAll(userId));
    }

    @Nullable
    public List<AbstractEntity> sortByStatus(@NotNull String userId) {
        return projectRepository.sortByStatus(findAll(userId));
    }

}
