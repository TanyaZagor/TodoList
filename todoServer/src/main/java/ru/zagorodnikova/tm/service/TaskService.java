package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.util.UtilDateFormatter;

import java.util.Date;
import java.util.List;


public class TaskService extends AbstractService implements ITaskService {

    @NotNull private final ITaskRepository<AbstractEntity> taskRepository;
    @NotNull private final IProjectRepository<AbstractEntity> projectRepository;

    public TaskService(@NotNull ITaskRepository<AbstractEntity> taskRepository, @NotNull IProjectRepository<AbstractEntity> projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;

    }

    @Nullable
    public AbstractEntity persistTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        if (projectName.isEmpty()) return null;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskName.isEmpty()) return null;
            if (description.isEmpty()) return null;
            if (dateStart.isEmpty()) return null;
            if (dateFinish.isEmpty()) return null;
            @NotNull final Date start = UtilDateFormatter.dateFormatter(dateStart);
            @NotNull final Date finish = UtilDateFormatter.dateFormatter(dateFinish);
            @Nullable final Task task = new Task(userId, project.getId(), taskName, description, start, finish);
            return taskRepository.persist(task);
        }
        return null;
    }

    public void removeTask(@NotNull String userId, @NotNull String projectName, @NotNull String taskName){
        if (projectName.isEmpty()) return;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            @NotNull final Task task = new Task();
            task.setUserId(userId);
            task.setProjectId(project.getId());
            task.setName(taskName);
            taskRepository.remove(task);
        }
    }

    public void removeAllTasks(@NotNull String userId) {
        @NotNull final Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }

    public void removeAllTasksInProject(@NotNull String userId, @NotNull String projectName){
        if (projectName.isEmpty()) return;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            @NotNull final Task task = new Task();
            task.setProjectId(project.getId());
            taskRepository.removeAllInProject(task);
        }
    }

    public void mergeTask(@NotNull String userId, @NotNull String projectName, @NotNull String oldTaskName, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        if (description.isEmpty()) return;
        if (dateStart.isEmpty()) return;
        if (dateFinish.isEmpty()) return;
        final Task task = (Task) findOneTask(userId, projectName, oldTaskName, "");
        if (task != null) {
            @NotNull final Date start = UtilDateFormatter.dateFormatter(dateStart);
            @NotNull final Date finish = UtilDateFormatter.dateFormatter(dateFinish);
            task.setName(taskName);
            task.setDescription(description);
            task.setDateStart(start);
            task.setDateFinish(finish);
            taskRepository.merge(task);
        }
    }

    @Nullable
    public List<AbstractEntity> findAllTasksInProject(@NotNull String userId, @NotNull String projectName) {
        if (projectName.isEmpty()) return null;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            @NotNull final Task task = new Task();
            task.setProjectId(project.getId());
            return  taskRepository.findAll(task);
        }
        return null;
    }


    @Nullable
    public List<AbstractEntity> findAllTasks(@NotNull String userId) {
        @NotNull final Task task = new Task();
        task.setUserId(userId);
        return  taskRepository.findAllTasks(task);
    }

    @Nullable
    public AbstractEntity findOneTask(@NotNull String userId, @NotNull String projectName,@NotNull String taskName, @NotNull String taskDescription) {
        if (projectName.isEmpty()) return null;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = (Project) projectRepository.findOne(newProject);
        if (project != null) {
            if (taskDescription.isEmpty()) {
                if (taskName.isEmpty()) return null;
                @NotNull Task task = new Task();
                task.setProjectId(project.getId());
                task.setName(taskName);
                return taskRepository.findOne(task);
            }
            @NotNull final Task task = new Task();
            task.setProjectId(project.getId());
            task.setDescription(taskDescription);
            return taskRepository.findOne(task);
        }
        return null;
    }
    @Nullable
    public List<AbstractEntity> sortTasksByDateCreated(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<AbstractEntity> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByDateCreated(list);
        }
        return null;
    }

    @Nullable
    public List<AbstractEntity> sortTasksByDateStart(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<AbstractEntity> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByDateStart(list);
        }
        return null;
    }
    @Nullable
    public List<AbstractEntity> sortTasksByDateFinish(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<AbstractEntity> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByDateFinish(list);
        }
        return null;
    }

    @Nullable
    public List<AbstractEntity> sortTasksByStatus(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<AbstractEntity> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByStatus(list);
        }
        return null;

    }

}
