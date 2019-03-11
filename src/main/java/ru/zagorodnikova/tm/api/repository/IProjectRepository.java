package ru.zagorodnikova.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectRepository<T extends AbstractEntity> {

    @Nullable
    T persist(@NotNull T t);

    void remove(@NotNull T t);

    void removeAll(@NotNull T t);

    @Nullable
    T findOne(@NotNull T t);

    void merge(@NotNull T t);

    @Nullable
    List<T> findAll(@NotNull T t);

}
