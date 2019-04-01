package ru.zagorodnikova.tm.api.repository;

import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.entity.Session;

public interface ISessionRepository {

    void persist(@NotNull final Session session);

    void remove(@NotNull final Session session);

    Session findOne(@NotNull final String id);
}
