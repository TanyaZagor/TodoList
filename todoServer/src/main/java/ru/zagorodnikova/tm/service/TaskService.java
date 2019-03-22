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
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = projectRepository.findOne(newProject);
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
        @Nullable Task task = findOneTask(userId, projectName, taskName, "");
        if (task != null) {
            taskRepository.remove(task);
        }

    }

    public void removeAllTasks(@NotNull final String userId) throws Exception {
        @NotNull final Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }

    public void removeAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) throws Exception {
        if (projectName.isEmpty()) return;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = projectRepository.findOne(newProject);
        if (project != null) {
            @NotNull final Task task = new Task();
            task.setProjectId(project.getId());
            taskRepository.removeAllInProject(task);
        }
    }

    public void mergeTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String oldTaskName,
                          @NotNull final String taskName, @NotNull final String description, @NotNull final String dateStart,
                          @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        if (description.isEmpty()) return;
        if (dateStart.isEmpty()) return;
        if (dateFinish.isEmpty()) return;
        final Task task = findOneTask(userId, projectName, oldTaskName, "");
        if (task != null) {
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            task.setName(taskName);
            task.setDescription(description);
            task.setDateStart(start);
            task.setDateFinish(finish);
            taskRepository.merge(task);
        }
    }

    @Nullable
    public List<Task> findAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = projectRepository.findOne(newProject);
        if (project != null) {
            @NotNull final Task task = new Task();
            task.setProjectId(project.getId());
            return  taskRepository.findAllTasksInProject(task);
        }
        return null;
    }


    @Nullable
    public List<Task> findAllTasks(@NotNull final String userId) {
        @NotNull final Task task = new Task();
        task.setUserId(userId);
        return  taskRepository.findAllTasks(task);
    }

    @Nullable
    public Task findOneTask(@NotNull final String userId, @NotNull final String projectName,
                            @NotNull final String taskName, @NotNull final String taskDescription) {
        if (projectName.isEmpty()) return null;
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        @Nullable final Project project = projectRepository.findOne(newProject);
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
