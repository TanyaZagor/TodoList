package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;

import java.util.List;

public interface IUserService<T extends AbstractEntity> {

    @Nullable
    T signIn( @NotNull String login, @NotNull String password);

    @Nullable
    T signUp(@NotNull String login, @NotNull String password, @NotNull String fistName, @NotNull String lastName, @NotNull String email);

    void changePassword(@NotNull String userId, @NotNull String login, @NotNull String oldPassword, @NotNull String newPassword);

    void update(@NotNull String userId, @NotNull String firstName, @NotNull String lastName, @NotNull String email);

    void removeAll(@NotNull String userId);

    void remove(@NotNull String userId);

    @Nullable
    List<T> findAll(@NotNull RoleType roleType);

}
