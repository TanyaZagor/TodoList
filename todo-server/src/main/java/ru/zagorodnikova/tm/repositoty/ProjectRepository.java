package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public void persist(@NotNull Project project) {
        entityManager.persist(project);
    }

    @Override
    public void remove(@NotNull Project project) {
        entityManager.remove(project);
    }

    @Override
    public void removeAll(@NotNull String userId) {
        entityManager.createQuery("Delete from Project project where project.userId = ?1")
                .setParameter(1, userId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Project findOne(@NotNull String userId, @NotNull String name) {
        Project project = (Project) entityManager.createQuery("Select project from Project project where project.userId = ?1 and project.name = ?2")
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
        List list = entityManager.createQuery("Select project from Project project where project.userId = ?1")
                .setParameter(1,userId)
                .getResultList();
        return list;
    }

    @NotNull
    @Override
    public List<Project> getProjects() {
        List list = entityManager.createQuery("Select project from Project project")
                .getResultList();
        return list;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
