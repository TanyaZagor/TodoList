package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;

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
        entityManager.createQuery("Delete from Task task where task.userId = ?1")
                .setParameter(1, userId)
                .executeUpdate();
    }

    @Override
    public void removeAllInProject(@NotNull String projectId) {
        entityManager.createQuery("Delete from Task task where task.projectId = ?1")
                .setParameter(1, projectId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Task findOne(@NotNull String projectId, @NotNull String name) {
        Task task = (Task) entityManager.createQuery("Select task from Task task where task.projectId = ?1 and task.name = ?2")
                .setParameter(1, projectId)
                .setParameter(2,name)
                .getSingleResult();
        return task;
    }

    @Nullable
    @Override
    public List<Task> findAllTasksInProject(@NotNull String projectId) {
        List list = entityManager.createQuery("Select task from Task task where task.projectId = ?1 ")
                .setParameter(1, projectId)
                .getResultList();
        return list;
    }

    @Nullable
    @Override
    public List<Task> findAllTasks(@NotNull String userId) {
        List list = entityManager.createQuery("Select task from Task task where task.userId = ?1 ")
                .setParameter(1, userId)
                .getResultList();
        return list;
    }

    @NotNull
    @Override
    public List<Task> getTasks() {
        List list = entityManager.createQuery("Select task from Task task")
                .getResultList();
        return list;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
