package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public class UserService extends AbstractService implements IUserService {

    @NotNull private final IUserRepository<User> userRepository;

    public UserService(@NotNull final IUserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User signIn(@NotNull final String login, @NotNull final String password) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userRepository.signIn(user);
    }

    @Nullable
    public User signUp(@NotNull final String login, @NotNull final String password, @NotNull final String fistName,
                       @NotNull final String lastName, @NotNull final String email) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        if (fistName.isEmpty()) return null;
        if (lastName.isEmpty()) return null;
        if (email.isEmpty()) return null;
        @NotNull final User user = new User(login, password, fistName, lastName, email);
        return userRepository.persist(user);
    }

    public void changePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String oldPassword,
                               @NotNull final String newPassword) throws Exception {
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

    public void updateUser(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName,
                           @NotNull String email) {
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

    public void removeUser(@NotNull final String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.remove(user);
    }

    @Nullable
    public List<User> findAllUsers(@NotNull final String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        return userRepository.findAll(user);
    }

    @NotNull
    @Override
    public User findOne(@NotNull final String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        return userRepository.findOne(user);
    }


}
