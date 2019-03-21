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

    public SessionService(@NotNull final SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Nullable
    public Session persist(@NotNull final User user) throws Exception {
        @NotNull final Session session = new Session(user.getId());
        session.setSignature(sessionRepository.signSession(session));
        return sessionRepository.persist(session);
    }

    public void remove(@NotNull final Session session) throws Exception {
        sessionRepository.remove(session);
    }

    public void validate(@NotNull final Session session) throws Exception {
        sessionRepository.validate(session);
    }
}
