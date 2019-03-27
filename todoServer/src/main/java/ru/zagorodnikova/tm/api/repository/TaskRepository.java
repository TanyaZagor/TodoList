package ru.zagorodnikova.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;

import java.util.Date;
import java.util.List;

public interface TaskRepository {

    @Insert("Insert app_task values (#{id}, #{userId}, #{projectId}, #{name}, #{description}, #{dateStart}, #{dateFinish}, #{dateCreate}, #{status})")
    void persist(@NotNull final Task task);

    @Update("Update app_task set name = #{name}, description = #{description}, dateStart = #{dateStart}, dateFinish = #{dateFinish}, status = #{status} where id= #{id}")
    void merge(@NotNull @Param("id") final String id,
               @NotNull @Param("name") final String name,
               @NotNull @Param("description") final String description,
               @NotNull @Param("dateStart") final Date dateStart,
               @NotNull @Param("dateFinish") final Date dateFinish,
               @NotNull @Param("status") final Status status);

    @Delete("Delete from app_task where id = #{id}")
    void remove(@NotNull @Param("id") String id);

    @Delete("Delete from app_task where user_id = #{userId}")
    void removeAll(@NotNull @Param("userId") final String userId);

    @Delete("Delete from app_task where project_id = #{projectId}")
    void removeAllInProject(@NotNull @Param("projectId") final String projectId);

    @Nullable
    @Select("Select * from app_task where project_id = #{projectId} and name = #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "dateStart", column = "dateStart"),
            @Result(property = "dateFinish", column = "dateFinish"),
            @Result(property = "dateCreate", column = "dateCreate"),
            @Result(property = "status", column = "status")
    })
    Task findOne(@NotNull @Param("projectId") final String projectId, @NotNull @Param("name") final String name);

    @Nullable
    @Select("Select * from app_task where project_id = #{projectId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "dateStart", column = "dateStart"),
            @Result(property = "dateFinish", column = "dateFinish"),
            @Result(property = "dateCreate", column = "dateCreate"),
            @Result(property = "status", column = "status")
    })
    List<Task> findAllTasksInProject(@NotNull @Param("projectId") final String projectId);

    @Nullable
    @Select("Select * from app_task where user_id = #{userId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "dateStart", column = "dateStart"),
            @Result(property = "dateFinish", column = "dateFinish"),
            @Result(property = "dateCreate", column = "dateCreate"),
            @Result(property = "status", column = "status")
    })
    List<Task> findAllTasks(@NotNull @Param("userId") final String userId);

    @NotNull
    @Select("Select * from app_task")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "dateStart", column = "dateStart"),
            @Result(property = "dateFinish", column = "dateFinish"),
            @Result(property = "dateCreate", column = "dateCreate"),
            @Result(property = "status", column = "status")
    })
    List<Task> getTasks();

}
