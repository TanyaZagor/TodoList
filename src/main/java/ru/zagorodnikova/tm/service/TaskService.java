package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;


public class TaskService extends AbstractService implements ITaskService {

    private final ITaskRepository<AbstractEntity> taskRepository;
    private final IProjectRepository<AbstractEntity> projectRepository;

    public TaskService(@NotNull ITaskRepository<AbstractEntity> taskRepository, @NotNull IProjectRepository<AbstractEntity> projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;

    }

    @Nullable
    public AbstractEntity persist(@NotNull String userId, @Nullable String projectName, @Nullable String taskName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return null;
            if (description == null || description.isEmpty()) return null;
            if (dateStart == null || dateStart.isEmpty()) return null;
            if (dateFinish == null || dateFinish.isEmpty()) return null;
            Task task = new Task(userId, project.getId(), taskName, description, dateStart, dateFinish);
            return taskRepository.persist(task);
        }
        return null;
    }

    public void remove(@NotNull String userId, @Nullable String projectName, @Nullable String taskName){
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            Task task = new Task();
            task.setUserId(userId);
            task.setProjectId(project.getId());
            task.setName(taskName);
            taskRepository.remove(task);
        }

    }

    public void removeAll(@NotNull String userId) {
        Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }

    public void removeAllInProject(@NotNull String userId, @Nullable String projectName) {
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            Task task = new Task();
            task.setProjectId(project.getId());
            taskRepository.removeAllInProject(task);
        }
    }

    public void merge(@NotNull String userId, @Nullable String projectName, @Nullable String oldTaskName, @Nullable String taskName, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish) {
        if (projectName == null || projectName.isEmpty()) return;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskName == null || taskName.isEmpty()) return;
            if (description == null || description.isEmpty()) return;
            if (dateStart == null || dateStart.isEmpty()) return;
            if (dateFinish == null || dateFinish.isEmpty()) return;
            Task task = (Task) findOne(userId, projectName, oldTaskName, null);
            task.setName(taskName);
            task.setDescription(description);
            task.setDateStart(dateStart);
            task.setDateFinish(dateFinish);
            taskRepository.merge(task);
        }

    }

    @Nullable
    public List<AbstractEntity> findAll(@NotNull String userId, @Nullable String projectName) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            Task task = new Task();
            task.setProjectId(project.getId());
            return  taskRepository.findAll(task);
        }
        return null;
    }

    @Nullable
    public AbstractEntity findOne(@NotNull String userId, @Nullable String projectName,@Nullable String taskName, @Nullable String taskDescription) {
        if (projectName == null || projectName.isEmpty()) return null;
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskDescription == null || taskDescription.isEmpty()) {
                if (taskName == null || taskName.isEmpty()) return null;
                Task task = new Task();
                task.setProjectId(project.getId());
                task.setName(taskName);
                return taskRepository.findOne(task);
            }
            Task task = new Task();
            task.setProjectId(project.getId());
            task.setDescription(taskDescription);
            return taskRepository.findOne(task);
        }
        return null;
    }
    @Nullable
    public List<AbstractEntity> sortByDateCreated(@NotNull String userId, @Nullable String projectName) {
        return taskRepository.sortByDateCreated(findAll(userId, projectName));
    }

    @Nullable
    public List<AbstractEntity> sortByDateStart(@NotNull String userId, @Nullable String projectName) {
        return taskRepository.sortByDateStart(findAll(userId, projectName));
    }
    @Nullable
    public List<AbstractEntity> sortByDateFinish(@NotNull String userId, @Nullable String projectName) {
        return taskRepository.sortByDateFinish(findAll(userId, projectName));
    }

    @Nullable
    public List<AbstractEntity> sortByStatus(@NotNull String userId, @Nullable String projectName) {
        return taskRepository.sortByStatus(findAll(userId, projectName));
    }

}
