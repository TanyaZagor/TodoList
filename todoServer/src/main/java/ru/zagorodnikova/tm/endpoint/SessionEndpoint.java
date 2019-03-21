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

    @NotNull
    private ServiceLocator serviceLocator;

    public SessionEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Nullable
    @Override
    public Session signIn(@WebParam(name = "login") @NotNull String login,
                          @WebParam(name = "password") @NotNull String password) throws Exception {

        final User user = serviceLocator.getUserService().signIn(login, password);
        if (user != null) {
            return serviceLocator.getSessionService().persist(user);
        }
        return null;
    }

    @Nullable
    @Override
    public Session signUp(@WebParam(name = "login") @NotNull String login,
                          @WebParam(name = "password") @NotNull String password,
                          @WebParam(name = "firstName") @NotNull String fistName,
                          @WebParam(name = "lastName") @NotNull String lastName,
                          @WebParam(name = "email") @NotNull String email) throws Exception {

        final User user = serviceLocator.getUserService().signUp(login, password, fistName, lastName, email);
        if (user != null) {
            return serviceLocator.getSessionService().persist(user);
        }
        return null;
    }

    @Override
    public void remove(@WebParam(name = "session") @NotNull Session session) throws Exception {
        serviceLocator.getSessionService().remove(session);
    }


}
