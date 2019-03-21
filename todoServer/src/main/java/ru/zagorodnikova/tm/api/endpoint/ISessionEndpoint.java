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
    Session signIn(@WebParam(name = "login") @NotNull String login,
                   @WebParam(name = "password") @NotNull String password) throws Exception;

    @WebMethod
    @Nullable
    Session signUp(@WebParam(name = "login") @NotNull String login,
                   @WebParam(name = "password") @NotNull String password,
                   @WebParam(name = "firstName") @NotNull String fistName,
                   @WebParam(name = "lastName") @NotNull String lastName,
                   @WebParam(name = "email") @NotNull String email) throws Exception;

    @WebMethod
    void remove(@WebParam(name = "session") @NotNull Session session) throws Exception;
}
