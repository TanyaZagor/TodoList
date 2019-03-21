package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IUserEndpoint {

    @WebMethod
    void changePassword(@WebParam(name = "session") @NotNull Session session,
                        @WebParam(name = "login") @NotNull String login,
                        @WebParam(name = "oldPassword") @NotNull String oldPassword,
                        @WebParam(name = "newPassword") @NotNull String newPassword) throws Exception;

    @WebMethod
    void updateUser(@WebParam(name = "session") @NotNull Session session,
                    @WebParam(name = "firstName") @NotNull String firstName,
                    @WebParam(name = "lastName") @NotNull String lastName,
                    @WebParam(name = "email") @NotNull String email) throws Exception;

    @WebMethod
    void removeUser(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    @Nullable
    List<User> findAllUsers(@WebParam(name = "session") @NotNull Session session) throws Exception;


    @WebMethod
    @NotNull
    User findUser(@WebParam(name = "session") @NotNull Session session) throws Exception;
}
