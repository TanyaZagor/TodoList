package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@ApplicationScoped
@NoArgsConstructor
public class TaskService implements ITaskService {

    @Inject
    private IProjectRepository projectRepository;

    @Inject
    private ITaskRepository taskRepository;

    @Nullable
    @Transactional
    public Task persistTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String taskName,
                            @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return null;
        if (taskName.isEmpty()) return null;
        if (description.isEmpty()) return null;
        if (dateStart.isEmpty()) return null;
        if (dateFinish.isEmpty()) return null;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project == null) return null;
        @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
        @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
        @Nullable final Task task = new Task(userId, project.getId(), taskName, description, start, finish);
        taskRepository.persist(task);
        return task;
    }

    @Transactional
    public void removeTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String taskName) {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        Project project = projectRepository.findOne(userId, projectName);
        if (project == null) return;
        @Nullable final Task task = taskRepository.findOne(project.getId(), taskName);
        if (task == null) return;
        taskRepository.remove(task);
    }

    @Transactional
    public void removeAllTasks(@NotNull final String userId) {
        taskRepository.removeAll(userId);
    }

    @Transactional
    public void removeAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return;
        Project project = projectRepository.findOne(userId, projectName);
        if (project == null) return;
        taskRepository.removeAllInProject(project.getId());
    }

    @Transactional
    public void mergeTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String oldTaskName,
                          @NotNull final String taskName, @NotNull final String description, @NotNull final String dateStart,
                          @NotNull final String dateFinish, @NotNull final String status) throws Exception {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        if (description.isEmpty()) return;
        if (dateStart.isEmpty()) return;
        if (dateFinish.isEmpty()) return;
        Project project = projectRepository.findOne(userId, projectName);
        if (project == null) return;
        Task task = taskRepository.findOne(project.getId(), oldTaskName);
        if (task == null) return;
        @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
        @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
        @NotNull final Status newStatus = createStatus(status);
        task.setName(taskName);
        task.setDescription(description);
        task.setDateStart(start);
        task.setDateFinish(finish);
        task.setStatus(newStatus);
        taskRepository.merge(task);
    }

    @Nullable
    @Transactional
    public List<Task> findAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) {
        try {
            Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return null;
            return taskRepository.findAllTasksInProject(project.getId());
        } catch (NoResultException e) {
            return null;
        }
    }


    @Nullable
    @Transactional
    public List<Task> findAllTasks(@NotNull final String userId) {
        try {
            return taskRepository.findAllTasks(userId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Transactional
    public Task findOneTask(@NotNull final String userId, @NotNull final String projectName,
                            @NotNull final String taskName) {
        if (projectName.isEmpty()) return null;
        if (taskName.isEmpty()) return null;
        try {
            Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return null;
            return taskRepository.findOne(project.getId(), taskName);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    public List<Task> sortTasksByDateCreated(@NotNull String userId, @NotNull String projectName) {
        if (projectName.isEmpty()) return null;
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            list.sort(((o1, o2) -> o2.getDateCreate().compareTo(o1.getDateCreate())));
            Collections.reverse(list);
            return list;
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByDateStart(@NotNull String userId, @NotNull String projectName) {
        if (projectName.isEmpty()) return null;
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            list.sort(((o1, o2) -> Objects.requireNonNull(o2.getDateStart()).compareTo(Objects.requireNonNull(o1.getDateStart()))));
            Collections.reverse(list);
            return list;
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByDateFinish(@NotNull String userId, @NotNull String projectName) {
        if (projectName.isEmpty()) return null;
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            list.sort(((o1, o2) -> Objects.requireNonNull(o2.getDateFinish()).compareTo(Objects.requireNonNull(o1.getDateFinish()))));
            Collections.reverse(list);
            return list;
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByStatus(@NotNull String userId, @NotNull String projectName) {
        if (projectName.isEmpty()) return null;
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            list.sort(((o1, o2) -> o2.getStatus().compareTo(o1.getStatus())));
            Collections.reverse(list);
            return list;
        }
        return null;
    }

    @Nullable
    @Transactional
    public List<Task> getTasks() {
        try {
            return taskRepository.getTasks();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void setTasks(@NotNull final List<Task> list){
        for (Task task : list) {
            taskRepository.persist(task);
        }
    }

    private Status createStatus(String status) {
        switch (status) {
            case "scheduled": return Status.SCHEDULED;
            case "in progress": return Status.IN_PROGRESS;
            case "done" : return Status.DONE;
            default: return Status.SCHEDULED;
        }
    }

}
