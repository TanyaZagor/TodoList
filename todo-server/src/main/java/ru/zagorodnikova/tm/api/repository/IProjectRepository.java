package ru.zagorodnikova.tm.api.repository;

import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public interface IProjectRepository {

    void persist(@NotNull final Project project);

    void remove(@NotNull final Project project);

    void  removeAll(@NotNull final String userId);

    @Nullable
    Project findOne(@NotNull final String userId, @NotNull final String name);

    void merge(@NotNull final Project project);

    @Nullable
    List<Project> findAll(@NotNull final String userId);

    @NotNull
    List<Project> getProjects();

    @NotNull
    EntityManager getEntityManager();
}
