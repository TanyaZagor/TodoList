package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.SessionRepository;
import ru.zagorodnikova.tm.repository.UserRepository;

import java.rmi.AccessException;

public class SessionService implements ISessionService {

    @NotNull private final SessionRepository sessionRepository;

    public SessionService(@NotNull SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Nullable
    public Session persist(@NotNull User user) {
        @NotNull final Session session = new Session(user.getId());
        session.setSignature(sessionRepository.signSession(session));
        return sessionRepository.persist(session);
    }

    public void remove(@NotNull Session session) {
        sessionRepository.remove(session);
    }

    public void validate(@NotNull Session session) {
        sessionRepository.validate(session);
    }
}
