package ru.zagorodnikova.tm.repositoty;

import org.apache.deltaspike.data.api.Repository;
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
        entityManager.createQuery("DELETE FROM Project project WHERE project.userId =: userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Nullable
    @Override
    public Project findOne(@NotNull String userId, @NotNull String name) {
        Project project = entityManager.createQuery("SELECT project FROM Project project WHERE project.userId =: userId AND project.name =: name", Project.class)
                .setParameter("userId",userId)
                .setParameter("name", name)
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
        List<Project> list = entityManager.createQuery("SELECT project FROM Project project WHERE project.userId =: userId", Project.class)
                .setParameter("userId",userId)
                .getResultList();
        return list;
    }

    @NotNull
    @Override
    public List<Project> getProjects() {
        List<Project> list = entityManager.createQuery("SELECT project FROM Project project", Project.class)
                .getResultList();
        return list;
    }

    @NotNull
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
