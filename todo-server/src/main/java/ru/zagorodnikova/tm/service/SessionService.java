package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repositoty.SessionRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.rmi.AccessException;
import java.util.Properties;

@ApplicationScoped
@NoArgsConstructor
public class SessionService implements ISessionService {

    @Inject
    private SessionRepository sessionRepository;

    @Nullable
    @Transactional
    public Session persist(@NotNull final User user) throws Exception {
        @NotNull final Session session = new Session(user.getId());
        session.setSignature(signSession(session));
        sessionRepository.persist(session);
        return session;
    }

    @Transactional
    public void remove(@NotNull final Session sessionIn) {
        try {
            @Nullable final Session session = sessionRepository.findBy(sessionIn.getId());
            if(session == null) return;
            sessionRepository.remove(session);
        } catch (NoResultException e) {
            return;
        }
    }

    @NotNull
    private String signSession(@NotNull final Session session) throws Exception {
        @NotNull final Properties property = new Properties();
        property.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        @NotNull final int cycle = Integer.valueOf( property.getProperty("cycle"));
        @NotNull final String salt = property.getProperty("salt");
        String signature = "";
        for (int i = 0; i < cycle; i++) {
            signature = PasswordUtil.hashPassword(salt + session.getUserId() + salt);
        }
        return signature;
    }

    @Transactional
    public void validate(final Session session) throws Exception{
        if(session == null) throw new AccessException("not valid session");
        if(session.getSignature() == null) throw new AccessException("not valid session");
        if(session.getUserId() == null) throw new AccessException("not valid session");
        if (session.getTimestamp() == 0) throw new AccessException("not valid session");
        @Nullable final Session temp = session.clone();
        if(temp == null) throw new AccessException("not valid session");
        @NotNull final String sourceSignature = signSession(session);
        @NotNull final String targetSignature = signSession(session);
        if(!sourceSignature.equals(targetSignature)) throw new AccessException("not valid session");
        try {
            if (sessionRepository.findBy(session.getId()) == null) throw new AccessException("not valid session");
        } catch (NoResultException e) {
            throw new AccessException("not valid session");
        }
    }
}
