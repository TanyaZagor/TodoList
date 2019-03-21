package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.util.UtilPassword;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.AccessException;
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
        validate(session);
        sessions.remove(session.getId());
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

    public void validate(Session session){
        if(session == null) throw new RuntimeException("not valid session");
        else if(session.getSignature() == null) throw new RuntimeException("not valid session");
        else if(session.getUserId() == null) throw new RuntimeException("not valid session");
        else if (session.getDate() == null) throw new RuntimeException("not valid session");
        final Session temp = session.clone();
        if(temp == null) throw new RuntimeException("not valid session");
        String sourceSignature = signSession(session);
        String targetSignature = signSession(session);
        boolean check = sourceSignature.equals(targetSignature);
        if(!check) throw new RuntimeException("not valid session");
        if (!sessions.containsKey(session.getId())) throw new RuntimeException("not valid session");
    }
}
