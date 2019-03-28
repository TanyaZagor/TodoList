package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
public interface ISessionEndpoint {

    @WebMethod
    @Nullable
    Session signIn(@WebParam(name = "login") @NotNull final String login,
                   @WebParam(name = "password") @NotNull final String password) throws Exception;

    @WebMethod
    @Nullable
    Session signUp(@WebParam(name = "login") @NotNull final String login,
                   @WebParam(name = "password") @NotNull final String password,
                   @WebParam(name = "firstName") @NotNull final String fistName,
                   @WebParam(name = "lastName") @NotNull final String lastName,
                   @WebParam(name = "email") @NotNull final String email) throws Exception;

    @WebMethod
    void remove(@WebParam(name = "session") @NotNull final Session session) throws Exception;
}
