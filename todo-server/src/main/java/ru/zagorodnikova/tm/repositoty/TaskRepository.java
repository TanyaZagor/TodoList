package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public void persist(@NotNull Task task) {
        entityManager.persist(task);
    }

    @Override
    public void merge(@NotNull Task task) {
        entityManager.merge(task);
    }

    @Override
    public void remove(@NotNull Task task) {
        entityManager.remove(task);
    }

    @Override
    public void removeAll(@NotNull String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.userId =: userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void removeAllInProject(@NotNull String projectId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.projectId =: projectId")
                .setParameter("projectId", projectId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Task findOne(@NotNull String projectId, @NotNull String name) {
        Task task = entityManager.createQuery("SELECT task FROM Task task WHERE task.projectId =: projectId AND task.name =: name", Task.class)
                .setParameter("projectId", projectId)
                .setParameter("name",name)
                .getSingleResult();
        return task;
    }

    @Nullable
    @Override
    public List<Task> findAllTasksInProject(@NotNull String projectId) {
        List<Task> list = entityManager.createQuery("SELECT task FROM Task task WHERE task.projectId =: projectId", Task.class)
                .setParameter("projectId", projectId)
                .getResultList();
        return list;
    }

    @Nullable
    @Override
    public List<Task> findAllTasks(@NotNull String userId) {
        List<Task> list = entityManager.createQuery("SELECT task FROM Task task WHERE task.userId =: userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        return list;
    }

    @NotNull
    @Override
    public List<Task> getTasks() {
        List<Task> list = entityManager.createQuery("SELECT task FROM Task task", Task.class)
                .getResultList();
        return list;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
