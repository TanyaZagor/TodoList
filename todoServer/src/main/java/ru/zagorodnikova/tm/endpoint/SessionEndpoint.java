package ru.zagorodnikova.tm.endpoint;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.ISessionEndpoint;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebService;

@WebService
public class SessionEndpoint implements ISessionEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public SessionEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Nullable
    @Override
    public Session signIn(@NotNull String login, @NotNull String password) {
        @Nullable final User user = serviceLocator.getUserService().signIn(login, password);
        if (user != null) {
            return serviceLocator.getSessionService().persist(user);
        }
        return null;
    }

    @Nullable
    @Override
    public Session signUp(@NotNull String login, @NotNull String password, @NotNull String fistName, @NotNull String lastName, @NotNull String email) {
        @Nullable final User user = serviceLocator.getUserService().signUp(login, password, fistName, lastName, email);
        if (user != null) {
            return serviceLocator.getSessionService().persist(user);
        }
        return null;
    }

    @Override
    public void remove(@NotNull Session session) {
        serviceLocator.getSessionService().remove(session);
    }


}
