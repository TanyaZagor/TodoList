package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserService {

    @Nullable
    User signIn(@NotNull final String login,
                @NotNull final String password) throws Exception;

    @Nullable
    User signUp(@NotNull final String login,
                @NotNull final String password,
                @NotNull final String fistName,
                @NotNull final String lastName,
                @NotNull final String email) throws Exception;

    void changePassword(@NotNull final String userId,
                        @NotNull final String login,
                        @NotNull final String oldPassword,
                        @NotNull final String newPassword) throws Exception;

    void updateUser(@NotNull final String userId,
                    @NotNull final String firstName,
                    @NotNull final String lastName,
                    @NotNull final String email) throws Exception;

    void removeUser(@NotNull final String userId) throws Exception;

    @Nullable
    List<User> findAllUsers(@NotNull final String userId);

    @Nullable
    User findOne(@NotNull final String userId);

    void removeAll();

    @Nullable
    List<User> getUsers();

    void setUsers(@NotNull final List<User> list);
}
