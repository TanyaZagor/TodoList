package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository<T extends User> {

    @Nullable
    T signIn(@NotNull final T t);

    void changePassword(@NotNull final T t) throws Exception;

    @Nullable
    T persist(@NotNull final T t) throws Exception;

    void remove(@NotNull final T t) throws Exception;

    void removeAll();

    @Nullable
    T findOne(@NotNull final T t);

    void merge(@NotNull final T t) throws Exception;

    @Nullable
    List<T> findAll(@Nullable final T t);

    boolean checkPassword(@NotNull final T t);

    @Nullable
    List<T> getUsers();

    void setUsers(@NotNull final List<T> list) throws Exception;

}
