package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserService {

    @Nullable
    User signIn(@NotNull String login, @NotNull String password);

    @Nullable
    User signUp(@NotNull String login, @NotNull String password, @NotNull String fistName, @NotNull String lastName, @NotNull String email);

    void changePassword(@NotNull String userId, @NotNull String login, @NotNull String oldPassword, @NotNull String newPassword);

    void updateUser(@NotNull String userId, @NotNull String firstName, @NotNull String lastName, @NotNull String email);

    void removeAllUsers(@NotNull String userId);

    void removeUser(@NotNull String userId);

    @Nullable
    List<User> findAllUsers(@NotNull String userId);

    @NotNull
    User findOne(@NotNull String userId);

}
