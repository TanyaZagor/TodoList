package ru.zagorodnikova.tm.api.mapper;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.util.Date;
import java.util.List;

public interface IProjectMapper {

    @Insert("Insert into app_project values (#{id}, #{userId}, #{name}, #{description}, #{dateFinish}, #{dateStart}, #{dateCreate})")
    void persist(@NotNull final Project project);

    @Delete("Delete from app_project where id = #{id}")
    int remove(@NotNull @Param("id") String id);

    @Delete("Delete from app_project where user_id = #{userId}")
    void  removeAll(@NotNull @Param("userId") final String userId);

    @Nullable
    @Select("Select * from app_project where user_id = #{userId} and name = #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "dateFinish", column = "dateFinish"),
            @Result(property = "dateStart", column = "dateStart"),
            @Result(property = "dateCreate", column = "dateCreate")
    })
    Project findOne(@NotNull @Param("userId") final String userId, @NotNull @Param("name") final String name);

    @Update("Update app_project set name = #{projectName}, description = #{description}, " +
            "dateStart = #{dateStart}, dateFinish = #{dateFinish} where id = #{id}")
    void merge(@NotNull @Param("id") final String id,
               @NotNull @Param("projectName") final String projectName,
               @NotNull @Param("description") final String description,
               @NotNull @Param("dateStart") final Date dateStart,
               @NotNull @Param("dateFinish") final Date dateFinish);

    @Nullable
    @Select("Select * from app_project where user_id = #{userId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "dateFinish", column = "dateFinish"),
            @Result(property = "dateStart", column = "dateStart"),
            @Result(property = "dateCreate", column = "dateCreate")
    })
    List<Project> findAll(@NotNull @Param("userId") final String userId);

    @NotNull
    @Select("Select * from app_project")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "dateFinish", column = "dateFinish"),
            @Result(property = "dateStart", column = "dateStart"),
            @Result(property = "dateCreate", column = "dateCreate")
    })
    List<Project> getProjects();
}
