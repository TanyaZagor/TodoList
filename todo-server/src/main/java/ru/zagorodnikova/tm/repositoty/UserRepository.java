package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User save(@NotNull final User user);

    @Nullable
    @Query(value = "SELECT user FROM User user WHERE user.login = :login AND user.password = :password")
    User signIn(@NotNull @Param("login") final String login,
                @NotNull @Param("password") final String password);

    void delete(@NotNull final User user);

    @Query(value = "DELETE FROM User user WHERE user.roleType = USER")
    void removeAll();

    @Nullable
    @Query(value = "SELECT user FROM User user WHERE user.id = :id")
    User findOne(@NotNull @Param("id") final String userId);

    @Nullable
    List<User> findAll();
}
