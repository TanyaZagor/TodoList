package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserRepository<T extends User> {

    @Nullable
    T signIn(@NotNull final T t);

    void changePassword(@NotNull final T t) throws Exception;

    @Nullable
    T persist(@NotNull final T t);

    void remove(@NotNull final T t);

    void removeAll();

    @NotNull
    T findOne(@NotNull final T t);

    void merge(@NotNull final T t);

    @Nullable
    List<T> findAll(@NotNull final T t);

    boolean checkPassword(@NotNull final T t);
    @NotNull
    List<T> getUsers();

    void setUsers(@NotNull final List<T> list);

}
