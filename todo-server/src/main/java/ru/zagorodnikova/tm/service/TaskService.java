package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.repositoty.ProjectRepository;
import ru.zagorodnikova.tm.repositoty.TaskRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
public class TaskService implements ITaskService {

    @Inject
    private EntityManagerFactory factory;

    @Nullable
    public Task persistTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String taskName,
                            @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return null;
        if (taskName.isEmpty()) return null;
        if (description.isEmpty()) return null;
        if (dateStart.isEmpty()) return null;
        if (dateFinish.isEmpty()) return null;
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            @Nullable final Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return null;
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            @Nullable final Task task = new Task(userId, project.getId(), taskName, description, start, finish);
            TaskRepository taskRepository = new TaskRepository(entityManager);
            entityManager.getTransaction().begin();
            taskRepository.persist(task);
            entityManager.getTransaction().commit();
            return task;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public void removeTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String taskName) {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return;
            @Nullable final Task task = taskRepository.findOne(project.getId(), taskName);
            if (task == null) return;
            entityManager.getTransaction().begin();
            taskRepository.remove(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeAllTasks(@NotNull final String userId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            entityManager.getTransaction().begin();
            taskRepository.removeAll(userId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return;
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return;
            entityManager.getTransaction().begin();
            taskRepository.removeAllInProject(project.getId());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
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
            entityManager.getTransaction().begin();
            taskRepository.merge(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    public List<Task> findAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return null;
            return taskRepository.findAllTasksInProject(project.getId());
        } catch (Exception e) {
            return null;
        }
    }


    @Nullable
    public List<Task> findAllTasks(@NotNull final String userId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            return taskRepository.findAllTasks(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    public Task findOneTask(@NotNull final String userId, @NotNull final String projectName,
                            @NotNull final String taskName) {
        if (projectName.isEmpty()) return null;
        if (taskName.isEmpty()) return null;
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return null;
            return taskRepository.findOne(project.getId(), taskName);
        } catch (Exception e) {
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
    @SneakyThrows
    public List<Task> getTasks() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            return taskRepository.getTasks();
        } catch (Exception e) {
            return null;
        }
    }

    public void setTasks(@NotNull final List<Task> list){
        EntityManager entityManager = factory.createEntityManager();
        try {
            TaskRepository taskRepository = new TaskRepository(entityManager);
            entityManager.getTransaction().begin();
            for (Task task : list) {
                taskRepository.persist(task);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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
