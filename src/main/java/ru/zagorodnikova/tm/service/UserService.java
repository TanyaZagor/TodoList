package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public class UserService extends AbstractService implements IUserService {

    private final IUserRepository<AbstractEntity> userRepository;

    public UserService(@NotNull IUserRepository<AbstractEntity> userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public AbstractEntity signIn(@Nullable String login, @Nullable String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userRepository.signIn(user);
    }

    @Nullable
    public AbstractEntity signUp(@Nullable String login, @Nullable String password, @Nullable String fistName, @Nullable String lastName, @Nullable String email, RoleType roleType) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        if (fistName == null || fistName.isEmpty()) return null;
        if (lastName == null || lastName.isEmpty()) return null;
        if (email == null || email.isEmpty()) return null;
        User user = new User(login, password, fistName, lastName, email, roleType);
        return userRepository.persist(user);
    }

    public void changePassword(@NotNull String userId, @Nullable String login, @Nullable String oldPassword, @Nullable String newPassword) {
        if (login == null || login.isEmpty()) return;
        if (oldPassword == null || oldPassword.isEmpty()) return;
        if (newPassword == null || newPassword.isEmpty()) return;
        User user = new User();
        user.setId(userId);
        user.setPassword(oldPassword);
        user.setLogin(login);
        if (userRepository.checkPassword(user)) {
            user.setPassword(newPassword);
            userRepository.changePassword(user);
        }

    }

    public void update(@NotNull String userId, @Nullable String firstName, @Nullable String lastName, @Nullable String email) {
        if (firstName == null || firstName.isEmpty()) return;
        if (lastName == null || lastName.isEmpty()) return;
        if (email == null || email.isEmpty()) return;
        User user = new User();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.merge(user);
    }

    public void remove(String userId) {
        User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

    public void removeAll(String userId) {
        User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

    @Nullable
    public List<AbstractEntity> findAll(RoleType roleType) {
        User user = new User();
        user.setRoleType(roleType);
        return userRepository.findAll(user);
    }
}
