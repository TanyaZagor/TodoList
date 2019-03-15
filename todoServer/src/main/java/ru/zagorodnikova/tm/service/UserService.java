package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public class UserService extends AbstractService implements IUserService {

    @NotNull private final IUserRepository<User> userRepository;

    public UserService(@NotNull IUserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User signIn(@NotNull String login, @NotNull String password) {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userRepository.signIn(user);
    }

    @Nullable
    public User signUp(@NotNull String login, @NotNull String password, @NotNull String fistName, @NotNull String lastName, @NotNull String email) {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        if (fistName.isEmpty()) return null;
        if (lastName.isEmpty()) return null;
        if (email.isEmpty()) return null;
        @NotNull final User user = new User(login, password, fistName, lastName, email);
        return userRepository.persist(user);
    }

    public void changePassword(@NotNull String userId, @NotNull String login, @NotNull String oldPassword, @NotNull String newPassword) {
        if (login.isEmpty()) return;
        if (oldPassword.isEmpty()) return;
        if (newPassword.isEmpty()) return;
        @NotNull final User user = new User();
        user.setId(userId);
        user.setPassword(oldPassword);
        user.setLogin(login);
        if (userRepository.checkPassword(user)) {
            user.setPassword(newPassword);
            userRepository.changePassword(user);
        }

    }

    public void updateUser(@NotNull String userId, @NotNull String firstName, @NotNull String lastName, @NotNull String email) {
        if (firstName.isEmpty()) return;
        if (lastName.isEmpty()) return;
        if (email.isEmpty()) return;
        @NotNull final User user = new User();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.merge(user);
    }

    public void removeUser(@NotNull String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

    public void removeAllUsers(@NotNull String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

    @Nullable
    public List<User> findAllUsers(@NotNull RoleType roleType) {
        @NotNull final User user = new User();
        user.setRoleType(roleType);
        return userRepository.findAll(user);
    }
}
