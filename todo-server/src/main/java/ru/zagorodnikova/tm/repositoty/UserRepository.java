package ru.zagorodnikova.tm.repositoty;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

@Repository(forEntity = User.class)
public interface UserRepository extends FullEntityRepository<User, String> {

    void persist(@NotNull final User user);

    @Nullable
    @Query(value = "SELECT user FROM User user WHERE user.login = :login AND user.password = :password", max = 1)
    User signIn(@NotNull @QueryParam("login") final String login,
                @NotNull @QueryParam("password") final String password);

    void remove(@NotNull final User user);

    @Modifying
    @Query(value = "DELETE FROM User user WHERE user.roleType = USER")
    void removeAll();

    @Nullable
    User findBy(@NotNull final String userId);

    @Nullable
    List<User> findAll();

    User merge(@NotNull final User user);
}
