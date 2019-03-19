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
    public Session persist(@NotNull User user) {
        return serviceLocator.getSessionService().persist(user);
    }
}
