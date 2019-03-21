package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.util.UtilPassword;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

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
    public String signSession(@NotNull Session session){
        InputStream inputStream;
        Properties property = new Properties();

        inputStream = this.getClass().getClassLoader().getResourceAsStream("app.properties");
        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int cycle = Integer.valueOf( property.getProperty("cycle"));
        String salt = property.getProperty("salt");
        String signature = "";
        for (int i = 0; i < cycle; i++) {
            signature = UtilPassword.hashPassword(salt + session.getUserId() + salt);
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
