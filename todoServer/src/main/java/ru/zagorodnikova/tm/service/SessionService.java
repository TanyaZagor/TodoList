package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.SessionRepository;

public class SessionService implements ISessionService {

    @NotNull private final SessionRepository sessionRepository;

    public SessionService(@NotNull SessionRepository sessionRepository, @NotNull IUserRepository<User> userRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session persist(@NotNull User user) {
        @NotNull final Session session = new Session();
        session.setUserId(user.getId());
        return session;
    }
}
