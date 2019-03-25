package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository<T extends User> {

    @Nullable
    T signIn(@NotNull final String login, @NotNull final String password);

    void changePassword(@NotNull final String userId, @NotNull final String password) throws Exception;

    @Nullable
    T persist(@NotNull final T t) throws Exception;

    void remove(@NotNull final String userId) throws Exception;

    void removeAll();

    void merge(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName, @NotNull final String email) throws Exception;

    @Nullable
    T findOne(@NotNull final String userId);

    boolean checkPassword(@NotNull final String login, @NotNull final String password);

    @Nullable
    List<T> getUsers();

    void setUsers(@NotNull final List<T> list) throws Exception;

}
