package ru.zagorodnikova.tm.repositoty;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

@Repository(forEntity = Project.class)
public interface ProjectRepository extends FullEntityRepository<Project, String> {

    void persist(@NotNull Project project);

    void remove(@NotNull Project project);

    Project merge(@NotNull Project project);

    @Modifying
    @Query(value = "DELETE FROM Project project WHERE project.userId = :userId")
    void  removeAll(@NotNull @QueryParam("userId") final String userId);

    @Nullable
    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId AND project.name = :name", max = 1)
    Project findOne(@NotNull @QueryParam("userId") final String userId, @NotNull @QueryParam("name") final String name);

    @Nullable
    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId")
    List<Project> findAllByUserId(@NotNull @QueryParam("userId") final String userId);

    @Nullable
    List<Project> findAll();
}
