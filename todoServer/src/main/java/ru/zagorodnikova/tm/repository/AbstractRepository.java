package ru.zagorodnikova.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class AbstractRepository<T extends AbstractEntity> {

    @NotNull private final Map<String, T> map = new LinkedHashMap<>();

    @Nullable
    public T persist(@NotNull final T t) {
        if (!map.containsValue(t)) {
            map.put(t.getId(), t);
            return t;
        }
        return null;
    }

    public void remove(@NotNull final T t) {
        map.remove(t.getId());
    }

    @Nullable
    abstract public T findOne(@NotNull final T t);

    abstract public void merge(@NotNull final T t);

    @Nullable
    abstract public List<T> findAll(@NotNull final T t);
}
