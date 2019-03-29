package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repositoty.SessionRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.rmi.AccessException;
import java.util.Properties;

@NoArgsConstructor
public class SessionService implements ISessionService {

    @Inject
    private EntityManagerFactory factory;

    @Nullable
    public Session persist(@NotNull final User user) throws Exception {
        EntityManager entityManager = factory.createEntityManager();
        try {
            SessionRepository sessionRepository = new SessionRepository(entityManager);
            @NotNull final Session session = new Session(user.getId());
            session.setSignature(signSession(session));
            entityManager.getTransaction().begin();
            sessionRepository.persist(session);
            entityManager.getTransaction().commit();
            return session;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public void remove(@NotNull final Session session) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            SessionRepository sessionRepository = new SessionRepository(entityManager);
            entityManager.getTransaction().begin();
            sessionRepository.remove(session);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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

    public void validate( final Session session) throws Exception{
        if(session == null) throw new AccessException("not valid session");
        if(session.getSignature() == null) throw new AccessException("not valid session");
        if(session.getUserId() == null) throw new AccessException("not valid session");
        if (session.getTimestamp() == 0) throw new AccessException("not valid session");
        @Nullable final Session temp = session.clone();
        if(temp == null) throw new AccessException("not valid session");
        @NotNull final String sourceSignature = signSession(session);
        @NotNull final String targetSignature = signSession(session);
        if(!sourceSignature.equals(targetSignature)) throw new AccessException("not valid session");
        EntityManager entityManager = factory.createEntityManager();
        try {
            SessionRepository sessionRepository = new SessionRepository(entityManager);
            if (sessionRepository.findOne(session.getId()) == null) throw new AccessException("not valid session");
        } catch (AccessException e) {
            throw new AccessException("not valid session");
        }
    }
}
