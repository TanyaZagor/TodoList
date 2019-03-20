package ru.zagorodnikova.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IUserEndpoint;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
public class UserEndpoint implements IUserEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public UserEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void changePassword(@NotNull Session session, @NotNull String login, @NotNull String oldPassword, @NotNull String newPassword) {
        if (!serviceLocator.getSessionService().validate(session)) return;
        serviceLocator.getUserService().changePassword(session.getUserId(), login, oldPassword, newPassword);
    }

    public void updateUser(@NotNull Session session, @NotNull String firstName, @NotNull String lastName, @NotNull String email) {
        if (!serviceLocator.getSessionService().validate(session)) return;
        serviceLocator.getUserService().updateUser(session.getUserId(), firstName, lastName, email);
    }

    public void removeAllUsers(@NotNull Session session) {
        if (!serviceLocator.getSessionService().validate(session)) return;
        serviceLocator.getUserService().removeAllUsers(session.getUserId());
    }

    public void removeUser(@NotNull Session session) {
        if (!serviceLocator.getSessionService().validate(session)) return;
        serviceLocator.getUserService().removeUser(session.getUserId());
    }

    @Nullable
    public List<User> findAllUsers(@NotNull Session session) {
        if (!serviceLocator.getSessionService().validate(session)) return null;
        return serviceLocator.getUserService().findAllUsers(session.getUserId());
    }

    @Override
    public boolean checkRole(@NotNull Session session) {
        if (!serviceLocator.getSessionService().validate(session)) return false;
        return findUser(session).getRoleType() == RoleType.ADMIN;
    }

    @Nullable
    @Override
    public User findUser(@NotNull Session session) {
        if (!serviceLocator.getSessionService().validate(session)) return null;
        return serviceLocator.getUserService().findOne(session.getUserId());
    }

}
