package ru.zagorodnikova.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@WebService(endpointInterface = "ru.zagorodnikova.tm.api.service.IUserService")
@NoArgsConstructor
public class UserEndpoint{

    @NotNull
    private ServiceLocator serviceLocator;

    public UserEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public User signIn(@NotNull String login, @NotNull String password) {
        return serviceLocator.getUserService().signIn(login, password);
    }

    @Nullable
    public User signUp(@NotNull String login, @NotNull String password, @NotNull String fistName, @NotNull String lastName, @NotNull String email) {
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
    @XmlElement
    public List<User> findAllUsers(@NotNull RoleType roleType) {
        return serviceLocator.getUserService().findAllUsers(roleType);
    }
}
