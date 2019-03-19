package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IUserEndpoint {

    @WebMethod
    @Nullable
    User signIn(@NotNull String login,
                @NotNull String password);

    @WebMethod
    @Nullable
    User signUp(@NotNull String login,
                @NotNull String password,
                @NotNull String fistName,
                @NotNull String lastName,
                @NotNull String email);

    @WebMethod
    void changePassword(@NotNull String userId,
                        @NotNull String login,
                        @NotNull String oldPassword,
                        @NotNull String newPassword);

    @WebMethod
    void updateUser(@NotNull String userId,
                    @NotNull String firstName,
                    @NotNull String lastName,
                    @NotNull String email);

    @WebMethod
    void removeAllUsers(@NotNull String userId);

    @WebMethod
    void removeUser(@NotNull String userId);

    @WebMethod
    @Nullable
    List<User> findAllUsers(@NotNull RoleType roleType);

}
