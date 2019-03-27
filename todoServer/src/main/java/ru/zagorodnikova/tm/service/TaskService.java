package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.Date;
import java.util.List;


public class TaskService implements ITaskService {

    @NotNull private final ITaskRepository<Task> taskRepository;
    @NotNull private final IProjectRepository<Project> projectRepository;

    public TaskService(@NotNull final ITaskRepository<Task> taskRepository, @NotNull final IProjectRepository<Project> projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;

    }

    @Nullable
    public Task persistTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String taskName,
                            @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return null;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            if (taskName.isEmpty()) return null;
            if (description.isEmpty()) return null;
            if (dateStart.isEmpty()) return null;
            if (dateFinish.isEmpty()) return null;
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            @Nullable final Task task = new Task(userId, project.getId(), taskName, description, start, finish);
            return taskRepository.persist(task);
        }
        return null;
    }

    public void removeTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String taskName) throws Exception {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        @Nullable final Task task = findOneTask(userId, projectName, taskName);
        if (task != null) {
            taskRepository.remove(task.getId());
        }

    }

    public void removeAllTasks(@NotNull final String userId) throws Exception {
        taskRepository.removeAll(userId);
    }

    public void removeAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) throws Exception {
        if (projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            taskRepository.removeAllInProject(project.getId());
        }
    }

    public void mergeTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String oldTaskName,
                          @NotNull final String taskName, @NotNull final String description, @NotNull final String dateStart,
                          @NotNull final String dateFinish, @NotNull final String status) throws Exception {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        if (description.isEmpty()) return;
        if (dateStart.isEmpty()) return;
        if (dateFinish.isEmpty()) return;
        final Task task = findOneTask(userId, projectName, oldTaskName);
        if (task != null) {
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            taskRepository.merge(task.getId(), taskName, description, start, finish, status);
        }
    }

    @Nullable
    public List<Task> findAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            return  taskRepository.findAllTasksInProject(project.getId());
        }
        return null;
    }


    @Nullable
    public List<Task> findAllTasks(@NotNull final String userId) {
        return  taskRepository.findAllTasks(userId);
    }

    @Nullable
    public Task findOneTask(@NotNull final String userId, @NotNull final String projectName,
                            @NotNull final String taskName) {
        if (projectName.isEmpty()) return null;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            if (taskName.isEmpty()) return null;
            return taskRepository.findOne(project.getId(), taskName);
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByDateCreated(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByDateCreated(list);
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByDateStart(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByDateStart(list);
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByDateFinish(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByDateFinish(list);
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByStatus(@NotNull String userId, @NotNull String projectName) {
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            return taskRepository.sortByStatus(list);
        }
        return null;
    }

}
