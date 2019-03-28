package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IUserEndpoint;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserEndpoint implements IUserEndpoint {

    @NotNull private final ServiceLocator serviceLocator;

    public UserEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void changePassword(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "login") @NotNull final String login,
            @WebParam(name = "oldPassword") @NotNull final String oldPassword,
            @WebParam(name = "newPassword") @NotNull final String newPassword
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getUserService().changePassword(session.getUserId(), login, oldPassword, newPassword);
    }

    public void updateUser(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "firstName") @NotNull final String firstName,
            @WebParam(name = "lastName") @NotNull final String lastName,
            @WebParam(name = "email") @NotNull final String email
    ) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getUserService().updateUser(session.getUserId(), firstName, lastName, email);
    }


    public void removeUser(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getUserService().removeUser(session.getUserId());
    }

    @Nullable
    public List<User> findAllUsers(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getUserService().findAllUsers(session.getUserId());
    }

    @Nullable
    @Override
    public User findUser(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getUserService().findOne(session.getUserId());
    }

}