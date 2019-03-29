package ru.zagorodnikova.tm.endpoint;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.ISessionEndpoint;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SessionEndpoint implements ISessionEndpoint {

    private IUserService userService;

    @Inject
    private ISessionService sessionService;


    @Nullable
    @Override
    public Session signIn(
            @WebParam(name = "login") @NotNull final String login,
            @WebParam(name = "password") @NotNull final String password
    ) throws Exception {
        @Nullable final User user = userService.signIn(login, password);
        if (user != null) {
            return sessionService.persist(user);
        }
        return null;
    }

    @Nullable
    @Override
    public Session signUp(
            @WebParam(name = "login") @NotNull final String login,
            @WebParam(name = "password") @NotNull final String password,
            @WebParam(name = "firstName") @NotNull final String fistName,
            @WebParam(name = "lastName") @NotNull final String lastName,
            @WebParam(name = "email") @NotNull final String email
    ) throws Exception {
        @Nullable final User user = userService.signUp(login, password, fistName, lastName, email);
        if (user != null) {
            return sessionService.persist(user);
        }
        return null;
    }

    @Override
    public void remove(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.remove(session);
    }


}
