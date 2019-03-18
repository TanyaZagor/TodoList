package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserRepository<T extends User> {

    @Nullable
    T signIn(@NotNull T t);

    void changePassword(@NotNull T t);

    @Nullable
    T persist(@NotNull T t);

    void remove(@NotNull T t);

    void removeAll(@NotNull T t);

    @Nullable
    T findOne(@NotNull T t);

    void merge(@NotNull T t);

    @Nullable
    List<T> findAll(@NotNull T t);

    boolean checkPassword(@NotNull T t);

}
