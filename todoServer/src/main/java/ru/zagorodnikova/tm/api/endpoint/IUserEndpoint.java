package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IUserEndpoint {

    @WebMethod
    void changePassword(@NotNull Session session,
                        @NotNull String login,
                        @NotNull String oldPassword,
                        @NotNull String newPassword);

    @WebMethod
    void updateUser(@NotNull Session session,
                    @NotNull String firstName,
                    @NotNull String lastName,
                    @NotNull String email);

    @WebMethod
    void removeAllUsers(@NotNull Session session);

    @WebMethod
    void removeUser(@NotNull Session session);

    @WebMethod
    @Nullable
    List<User> findAllUsers(@NotNull Session session);

    @WebMethod
    @NotNull
    boolean checkRole(@NotNull Session session);

    @WebMethod
    @NotNull
    User findUser(@NotNull Session session);
}
