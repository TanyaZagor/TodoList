package ru.zagorodnikova.tm.repositoty;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;

@Repository(forEntity = Session.class)
public interface SessionRepository extends FullEntityRepository<Session, String> {

    void persist(@NotNull final Session session);

    void remove(@NotNull final Session session);

    @Nullable
    Session findBy(@NotNull final String id);
}
