package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface IUserService<T extends User> {

    @WebMethod
    @Nullable
    T signIn(@NotNull String login,
             @NotNull String password);

    @WebMethod
    @Nullable
    T signUp(@NotNull String login,
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
    List<T> findAllUsers(@NotNull RoleType roleType);

}
