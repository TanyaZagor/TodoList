package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.util.UtilPassword;

import java.util.LinkedHashMap;
import java.util.Map;

public class SessionRepository {

    @NotNull private final Map<String, Session> sessions = new LinkedHashMap<>();

    @Nullable
    public Session persist(@NotNull Session session) {
        sessions.put(session.getId(), session);
        return session;
    }

    public void remove(@NotNull Session session) {
        if (validate(session)) {
            sessions.remove(session.getId());
        }
    }


    @NotNull
    public String signSession(@NotNull Session session) {
        String signature = "";
        for (int i = 0; i < 250; i++) {
            signature = UtilPassword.hashPassword("asd" + session.getUserId() + "asd");
        }
        return signature;
    }

    public boolean validate(Session session) {
        if(session == null) return false;
        else if(session.getSignature() == null) return false;
        else if(session.getUserId() == null) return false;
        else if (session.getDate() == null) return false;
        final Session temp = session.clone();
        if(temp == null) return false;
        String sourceSignature = signSession(session);
        String targetSignature = signSession(session);
        boolean check = sourceSignature.equals(targetSignature);
        if(!check) return false;
        return sessions.containsKey(session.getId());
    }
}
