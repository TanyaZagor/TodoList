package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
public interface ISessionEndpoint {

    @WebMethod
    @Nullable
    Session signIn(@NotNull String login, @NotNull String password);

    @WebMethod
    @Nullable
    Session signUp(@NotNull String login, @NotNull String password, @NotNull String fistName, @NotNull String lastName, @NotNull String email);

    @WebMethod
    void remove(@NotNull Session session);
}
