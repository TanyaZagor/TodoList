package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.dto.UserDto;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IUserEndpoint {

    @WebMethod
    void changePassword(@WebParam(name = "session") @NotNull final Session session,
                        @WebParam(name = "login") @NotNull final String login,
                        @WebParam(name = "oldPassword") @NotNull final String oldPassword,
                        @WebParam(name = "newPassword") @NotNull final String newPassword) throws Exception;

    @WebMethod
    void updateUser(@WebParam(name = "session") @NotNull final Session session,
                    @WebParam(name = "firstName") @NotNull final String firstName,
                    @WebParam(name = "lastName") @NotNull final String lastName,
                    @WebParam(name = "email") @NotNull final String email) throws Exception;

    @WebMethod
    void removeUser(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    List<UserDto> findAllUsers(@WebParam(name = "session") @NotNull final Session session) throws Exception;


    @WebMethod
    @Nullable
    UserDto findUser(@WebParam(name = "session") @NotNull final Session session) throws Exception;
}
