package ru.zagorodnikova.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface UserRepository {

    @Insert("Insert into app_user values (#{id}, #{login}, #{password}, #{firstName}, #{lastName}, #{email}, #{roleType}, null, null, null)")
    void persist(@NotNull final User user);

    @Nullable
    @Select("Select * from app_user where login = #{login} and passwordHash = #{password}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "password", column = "passwordHash"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "roleType", column = "role")
    })
    User signIn(@NotNull @Param("login") final String login, @NotNull @Param("password") final String password);

    @Update("Update app_user passwordHash = #{password} where id = #{id}")
    void changePassword(@NotNull @Param("id") final String userId, @NotNull @Param("password") final String password);

    @Delete("Delete app_user where id = #{id}")
    void remove(@NotNull @Param("id") final String userId);

    @Delete("Delete app_user where role = user")
    void removeAll();

    @Nullable
    @Select("Select * from app_user where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "password", column = "passwordHash"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "roleType", column = "role")
    })
    User findOne(@NotNull @Param("id") final String userId);

    @Select("Select * from app_user where login = #{login} and passwordHash = #{password}")
    String checkPassword(@NotNull @Param("login") final String login, @NotNull @Param("password") final String password);

    @Nullable
    @Select("Select from app_user")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "password", column = "passwordHash"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "roleType", column = "role")
    })
    List<User> getUsers();

    @Update("Update app_user set firstName = #{firstName}, lastName = #{lastName}, email = #{email} where id = #{id}")
    void merge(@NotNull @Param("id") final String userId, @NotNull @Param("firstName") final String firstName, @NotNull @Param("lastName") final String lastName, @NotNull @Param("email") final String email);
}
