package ru.zagorodnikova.tm.endpoint;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.ISessionEndpoint;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SessionEndpoint implements ISessionEndpoint {

    @NotNull private final ServiceLocator serviceLocator;

    public SessionEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Nullable
    @Override
    public Session signIn(@WebParam(name = "login") @NotNull final String login,
                          @WebParam(name = "password") @NotNull final String password) throws Exception {

        final User user = serviceLocator.getUserService().signIn(login, password);
        if (user != null) {
            return serviceLocator.getSessionService().persist(user);
        }
        return null;
    }

    @Nullable
    @Override
    public Session signUp(@WebParam(name = "login") @NotNull final String login,
                          @WebParam(name = "password") @NotNull final String password,
                          @WebParam(name = "firstName") @NotNull final String fistName,
                          @WebParam(name = "lastName") @NotNull final String lastName,
                          @WebParam(name = "email") @NotNull final String email) throws Exception {

        final User user = serviceLocator.getUserService().signUp(login, password, fistName, lastName, email);
        if (user != null) {
            return serviceLocator.getSessionService().persist(user);
        }
        return null;
    }

    @Override
    public void remove(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().remove(session);
    }


}
