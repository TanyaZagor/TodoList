package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> {

    @Nullable
    abstract public T persist(@NotNull T t);

    abstract public void remove(@NotNull T t);

    abstract public void removeAll(@NotNull T t);

    @Nullable
    abstract public T findOne(@NotNull T t);

    abstract public void merge(@NotNull T t);

    @Nullable
    abstract public List<T> findAll(@NotNull T t);
}
