package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.util.UtilPassword;

import java.rmi.AccessException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class SessionRepository {

    @NotNull private final Map<String, Session> sessions = new LinkedHashMap<>();

    @Nullable
    public Session persist(@NotNull final Session session) {
        sessions.put(session.getId(), session);
        return session;
    }

    public void remove(@NotNull final Session session) throws Exception {
        validate(session);
        sessions.remove(session.getId());
    }


    @NotNull
    public String signSession(@NotNull final Session session) throws Exception {
        @NotNull final Properties property = new Properties();
        property.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        @NotNull final int cycle = Integer.valueOf( property.getProperty("cycle"));
        @NotNull final String salt = property.getProperty("salt");
        String signature = "";
        for (int i = 0; i < cycle; i++) {
            signature = UtilPassword.hashPassword(salt + session.getUserId() + salt);
        }
        return signature;
    }

    public void validate( final Session session) throws Exception {
        if(session == null) throw new AccessException("not valid session");
        if(session.getSignature() == null) throw new AccessException("not valid session");
        if(session.getUserId() == null) throw new AccessException("not valid session");
        if (session.getDate() == null) throw new AccessException("not valid session");
        @Nullable final Session temp = session.clone();
        if(temp == null) throw new AccessException("not valid session");
        @NotNull final String sourceSignature = signSession(session);
        @NotNull final String targetSignature = signSession(session);
        if(!sourceSignature.equals(targetSignature)) throw new AccessException("not valid session");
        if (!sessions.containsKey(session.getId())) throw new AccessException("not valid session");
    }
}
