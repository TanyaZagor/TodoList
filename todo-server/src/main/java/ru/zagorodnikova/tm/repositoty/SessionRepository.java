package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.ISessionRepository;
import ru.zagorodnikova.tm.entity.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class SessionRepository implements ISessionRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public void persist(@NotNull Session session) {
        entityManager.persist(session);
    }

    @Override
    public void remove(@NotNull Session session) {
        entityManager.remove(session);
    }

    @Override
    public Session findOne(@NotNull String id) {
        return entityManager.find(Session.class, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
