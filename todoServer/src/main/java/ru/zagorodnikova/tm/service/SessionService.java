package ru.zagorodnikova.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.repository.SessionRepository;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.PasswordUtil;

import java.rmi.AccessException;
import java.util.Properties;

public class SessionService implements ISessionService {

    @NotNull private final SqlSessionFactory sqlSessionFactory;

    public SessionService(@NotNull final ServiceLocator serviceLocator) {
        this.sqlSessionFactory = serviceLocator.getSessionFactory();
    }

    @Nullable
    public Session persist(@NotNull final User user) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
                @NotNull final Session session = new Session(user.getId());
                session.setSignature(signSession(session));
                sessionRepository.persist(session);
                sqlSession.commit();
                return session;
            } catch (Exception e) {
                sqlSession.rollback();
                return null;
            }
        }
    }

    public void remove(@NotNull final Session session) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
                sessionRepository.remove(session);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
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
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            if (sessionRepository.findOne(session.getId()) == null) throw new AccessException("not valid session");
        }
    }
}
