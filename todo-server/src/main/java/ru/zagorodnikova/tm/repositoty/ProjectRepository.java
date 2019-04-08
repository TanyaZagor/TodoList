package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {

    Project save(@NotNull Project project);

    void delete(@NotNull Project project);

    @Modifying
    @Query(value = "DELETE FROM Project project WHERE project.userId = :userId")
    void  removeAll(@NotNull @Param("userId") final String userId);

    @Nullable
    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId AND project.name = :name")
    Project findOne(@NotNull @Param("userId") final String userId, @NotNull @Param("name") final String name);

    @Nullable
    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId")
    List<Project> findAllByUserId(@NotNull @Param("userId") final String userId);

    @Nullable
    List<Project> findAll();
}
