package ru.zagorodnikova.tm.api.repository;

import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;

import javax.persistence.EntityManager;

public interface ISessionRepository {

    void persist(@NotNull final Session session);

    void remove(@NotNull final Session session);

    @Nullable
    Session findOne(@NotNull final String id);

    @NotNull
    EntityManager getEntityManager();
}
