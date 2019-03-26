package ru.zagorodnikova.tm.api.mapper;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.entity.Session;

public interface ISessionMapper {

    @Insert("Insert app_session values(#{id}, #{userId}, #{signature}, #{timestamp})")
    void persist(@NotNull final Session session);

    @Delete("Delete app_session where id = #{id}")
    void remove(@NotNull final Session session);

    @Select("Select * from app_session where id = #{id}")
    String findOne(@NotNull @Param("id") final String id);
}
