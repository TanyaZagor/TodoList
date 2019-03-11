package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;

import java.util.List;

public interface IUserService<T extends AbstractEntity> {

    @Nullable
    T signIn( @Nullable String login, @Nullable String password);

    @Nullable
    T signUp(@NotNull String login, @Nullable String password, @Nullable String fistName, @Nullable String lastName, @Nullable String email,@NotNull  RoleType roleType);

    void changePassword(@NotNull String userId, @Nullable String login, @Nullable String oldPassword, @Nullable String newPassword);

    void update(@NotNull String userId, @Nullable String firstName, @Nullable String lastName, @Nullable String email);

    void removeAll(@NotNull String userId);

    void remove(@NotNull String userId);

    @Nullable
    List<T> findAll(@NotNull RoleType roleType);
}
