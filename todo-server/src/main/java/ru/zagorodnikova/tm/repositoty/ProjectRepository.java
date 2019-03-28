package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    @NotNull private final EntityManager entityManager;

    public ProjectRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull Project project) {
        entityManager.persist(project);
    }

    @Override
    public void remove(@NotNull String id) {
        entityManager.remove(id);
    }

    @Override
    public void removeAll(@NotNull String userId) {
        entityManager.createQuery("Delete from app_project where user_id = ?1")
                .setParameter(1, userId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Project findOne(@NotNull String userId, @NotNull String name) {
        Project project = (Project) entityManager.createQuery("Select * from app_project where user_id = ?1 and name = ?2")
                .setParameter(1,userId)
                .setParameter(2, name)
                .getSingleResult();
        return project;
    }

    @Override
    public void merge(@NotNull Project project) {
        entityManager.merge(project);
    }

    @Nullable
    @Override
    public List<Project> findAll(@NotNull String userId) {
        List list = entityManager.createQuery("Select * from app_project where user_id = ?1")
                .setParameter(1,userId)
                .getResultList();
        return list;
    }

    @NotNull
    @Override
    public List<Project> getProjects() {
        List list = entityManager.createQuery("Select * from app_project")
                .getResultList();
        return list;
    }
}
