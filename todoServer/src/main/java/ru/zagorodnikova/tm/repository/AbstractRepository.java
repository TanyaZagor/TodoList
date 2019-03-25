package ru.zagorodnikova.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class AbstractRepository<T extends AbstractEntity> {

    @Nullable
    abstract public T persist(@NotNull final T t) throws Exception;

    public abstract void remove(@NotNull final T t) throws Exception;

    @Nullable
    abstract public T findOne(@NotNull final T t);

    abstract public void merge(@NotNull final T t) throws Exception;

}
