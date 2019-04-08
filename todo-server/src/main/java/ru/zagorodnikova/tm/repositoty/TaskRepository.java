package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {

    Task save(@NotNull final Task task);

    void delete(@NotNull Task task);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.userId = :userId")
    void removeAll(@NotNull @Param("userId") final String userId);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.projectId = :projectId")
    void removeAllInProject(@NotNull @Param("projectId") final String projectId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.projectId = :projectId AND task.name = :name")
    Task findOne(@NotNull @Param("projectId") final String projectId,
                 @NotNull @Param("name") final String name);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.projectId = :projectId")
    List<Task> findAllTasksInProject(@NotNull @Param("projectId") final String projectId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId")
    List<Task> findAllTasksByUserId(@NotNull @Param("userId") final String userId);

    @NotNull
    List<Task> findAll();

}
