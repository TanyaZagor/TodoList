package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    @NotNull private final EntityManager entityManager;

    public TaskRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull Task task) {
        entityManager.persist(task);
    }

    @Override
    public void merge(@NotNull Task task) {
        entityManager.merge(task);
    }

    @Override
    public void remove(@NotNull String id) {
        entityManager.remove(id);
    }

    @Override
    public void removeAll(@NotNull String userId) {
        entityManager.createQuery("Delete from app_task where user_id = ?1")
                .setParameter(1, userId)
                .executeUpdate();
    }

    @Override
    public void removeAllInProject(@NotNull String projectId) {
        entityManager.createQuery("Delete from app_task where project_id = ?1")
                .setParameter(1, projectId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Task findOne(@NotNull String projectId, @NotNull String name) {
        Task task = (Task) entityManager.createQuery("Select * from app_task where project_id = ?1 and name = ?2")
                .setParameter(1, projectId)
                .setParameter(2,name)
                .getSingleResult();
        return task;
    }

    @Nullable
    @Override
    public List<Task> findAllTasksInProject(@NotNull String projectId) {
        List list = entityManager.createQuery("Select from app_task where project_id = ?1 ")
                .setParameter(1, projectId)
                .getResultList();
        return list;
    }

    @Nullable
    @Override
    public List<Task> findAllTasks(@NotNull String userId) {
        List list = entityManager.createQuery("Select from app_task where user_id = ?1 ")
                .setParameter(1, userId)
                .getResultList();
        return list;
    }

    @NotNull
    @Override
    public List<Task> getTasks() {
        List list = entityManager.createQuery("Select from app_task")
                .getResultList();
        return list;
    }
}
