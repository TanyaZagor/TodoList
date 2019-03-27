package ru.zagorodnikova.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class TaskService implements ITaskService {

    @NotNull private final SqlSession sqlSession;
    @NotNull private final ProjectRepository projectRepository;
    @NotNull private final TaskRepository taskRepository;

    public TaskService(@NotNull final ServiceLocator serviceLocator) {
        this.sqlSession = serviceLocator.getSessionFactory().openSession();
        this.projectRepository = sqlSession.getMapper(ProjectRepository.class);
        this.taskRepository = sqlSession.getMapper(TaskRepository.class);
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
            try {
                taskRepository.persist(task);
                sqlSession.commit();
                return task;
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
        return null;
    }

    public void removeTask(@NotNull final String userId, @NotNull final String projectName, @NotNull final String taskName) {
        if (projectName.isEmpty()) return;
        if (taskName.isEmpty()) return;
        @Nullable final Task task = findOneTask(userId, projectName, taskName);
        if (task != null) {
            try {
                taskRepository.remove(task.getId());
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }

    }

    public void removeAllTasks(@NotNull final String userId) {
        try {
            taskRepository.removeAll(userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }
    }

    public void removeAllTasksInProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            try {
                taskRepository.removeAllInProject(project.getId());
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
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
            @NotNull final Status newStatus = createStatus(status);
            try {
                taskRepository.merge(task.getId(), taskName, description, start, finish, newStatus);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
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
            list.sort(((o1, o2) -> o2.getDateCreate().compareTo(o1.getDateCreate())));
            Collections.reverse(list);
            return list;
        }
        return null;
    }

    @Nullable
    public List<Task> sortTasksByDateStart(@NotNull String userId, @NotNull String projectName) {
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
        @Nullable final List<Task> list = findAllTasksInProject(userId, projectName);
        if (list != null) {
            list.sort(((o1, o2) -> o2.getStatus().compareTo(o1.getStatus())));
            Collections.reverse(list);
            return list;
        }
        return null;
    }

    @NotNull
    @SneakyThrows
    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    public void setTasks(@NotNull final List<Task> list){
        try {
            for (Task task : list) {
                taskRepository.persist(task);
            }
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
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
