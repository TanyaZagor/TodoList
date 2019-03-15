package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.zagorodnikova.tm.api.service.IUserService")
public class UserEndpoint{

    @NotNull
    private ServiceLocator serviceLocator;

    public UserEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public AbstractEntity signIn(@NotNull String login, @NotNull String password) {
        return serviceLocator.getUserService().signIn(login, password);
    }

    @Nullable
    public AbstractEntity signUp(@NotNull String login, @NotNull String password, @NotNull String fistName, @NotNull String lastName, @NotNull String email) {
        return serviceLocator.getUserService().signUp(login, password, fistName, lastName, email);
    }

    public void changePassword(@NotNull String userId, @NotNull String login, @NotNull String oldPassword, @NotNull String newPassword) {
        serviceLocator.getUserService().changePassword(userId, login, oldPassword, newPassword);
    }

    public void updateUser(@NotNull String userId, @NotNull String firstName, @NotNull String lastName, @NotNull String email) {
        serviceLocator.getUserService().updateUser(userId, firstName, lastName, email);
    }

    public void removeAllUsers(@NotNull String userId) {
        serviceLocator.getUserService().removeAllUsers(userId);
    }

    public void removeUser(@NotNull String userId) {
        serviceLocator.getUserService().removeUser(userId);
    }

    @Nullable
    public List findAllUsers(@NotNull RoleType roleType) {
        return serviceLocator.getUserService().findAllUsers(roleType);
    }
}
