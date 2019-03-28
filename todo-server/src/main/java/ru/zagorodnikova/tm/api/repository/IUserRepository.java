package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    void persist(@NotNull final User user);

    @Nullable
    User signIn(@NotNull final String login,
                @NotNull final String password);

    void remove(@NotNull final String userId);

    void removeAll();

    @Nullable
    User findOne(@NotNull final String userId);

    @Nullable
    List<User> getUsers();

    void merge(@NotNull final User user);
}
