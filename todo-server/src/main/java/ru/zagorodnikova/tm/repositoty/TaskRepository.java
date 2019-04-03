package ru.zagorodnikova.tm.repositoty;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

@Repository(forEntity = Task.class)
public interface TaskRepository extends FullEntityRepository<Task, String> {

    void persist(@NotNull final Task task);

    Task merge(@NotNull final Task task);

    void remove(@NotNull Task task);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.userId = :userId")
    void removeAll(@NotNull @QueryParam("userId") final String userId);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.projectId = :projectId")
    void removeAllInProject(@NotNull @QueryParam("projectId") final String projectId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.projectId = :projectId AND task.name = :name", max = 1)
    Task findOne(@NotNull @QueryParam("projectId") final String projectId,
                 @NotNull @QueryParam("name") final String name);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.projectId = :projectId")
    List<Task> findAllTasksInProject(@NotNull @QueryParam("projectId") final String projectId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId")
    List<Task> findAllTasksByUserId(@NotNull @QueryParam("userId") final String userId);

    @NotNull
    List<Task> findAll();

}
