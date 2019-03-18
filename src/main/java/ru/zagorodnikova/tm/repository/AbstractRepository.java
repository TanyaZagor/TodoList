package ru.zagorodnikova.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.*;

@Getter
public abstract class AbstractRepository<T extends AbstractEntity> {
    @NotNull private Map<String, T> map = new LinkedHashMap<>();

    @Nullable
    public T persist(@NotNull T t) {
        if (!map.containsValue(t)) {
            map.put(t.getId(), t);
            return t;
        }
        return null;
    }

    public void remove(@NotNull T t) {
        map.remove(t.getId());
    }

    abstract public void removeAll(@NotNull T t);

    @Nullable
    abstract public T findOne(@NotNull T t);

    abstract public void merge(@NotNull T t);

    @Nullable
    abstract public List<T> findAll(@NotNull T t);
}
