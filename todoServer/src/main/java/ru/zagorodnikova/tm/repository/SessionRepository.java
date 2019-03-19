package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;

import java.util.LinkedHashMap;
import java.util.Map;

public class SessionRepository {

    @NotNull Map<String, Session> sessions = new LinkedHashMap<>();

    @Nullable
    public Session persist(Session session) {
        if (!sessions.containsKey(session.getId())) {
            sessions.put(session.getId(), session);
            return session;
        }
        return null;
    }

    @Nullable
    public void remove(Session session) {
        sessions.remove(session.getId());
    }

}
