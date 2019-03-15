package ru.zagorodnikova.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectRepository<T extends Project> {

    @Nullable
    T persist(@NotNull T t);

    void remove(@NotNull T t);

    void removeAll(@NotNull T t);

    @Nullable
    T findOne(@NotNull T t);

    void merge(@NotNull T t);

    @Nullable
    List<T> findAll(@NotNull T t);

    @NotNull
    List<T> sortByDateCreated(@NotNull List<T> list);

    @NotNull
    List<T> sortByDateStart(@NotNull List<T> list);

    @NotNull
    List<T> sortByDateFinish(@NotNull List<T> list);

    @NotNull
    List<T> sortByStatus(@NotNull List<T> list);

}
