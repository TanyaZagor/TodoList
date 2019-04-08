package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repositoty.UserRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@NoArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Nullable
    @Transactional
    public User signIn(@NotNull final String login, @NotNull final String password) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        try {
            return userRepository.signIn(login, PasswordUtil.hashPassword(password));
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Transactional
    public User signUp(@NotNull final String login, @NotNull final String password, @NotNull final String fistName,
                       @NotNull final String lastName, @NotNull final String email) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        if (fistName.isEmpty()) return null;
        if (lastName.isEmpty()) return null;
        if (email.isEmpty()) return null;
        if (signIn(login, password) != null) return null;
        @NotNull final User user = new User(login, password, fistName, lastName, email);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void changePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String oldPassword,
                               @NotNull final String newPassword) throws Exception {
        if (login.isEmpty()) return;
        if (oldPassword.isEmpty()) return;
        if (newPassword.isEmpty()) return;
        User user = findOne(userId);
        if (user != null) {
            user.setPassword(newPassword);
        }
    }

    @Transactional
    public void updateUser(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName,
                           @NotNull String email){
        if (firstName.isEmpty()) return;
        if (lastName.isEmpty()) return;
        if (email.isEmpty()) return;
        User user = findOne(userId);
        if (user == null) return;
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Transactional
    public void removeUser(@NotNull final String userId) {
        User user = findOne(userId);
        if (user == null) return;
        userRepository.delete(user);
    }

    @Nullable
    @Override
    @Transactional
    public User findOne(@NotNull final String userId) {
        try {
            return userRepository.findOne(userId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void removeAll() {
        userRepository.removeAll();
    }

    @Nullable
    @Transactional
    public List<User> getUsers() {
        try {
            return userRepository.findAll();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void setUsers(@NotNull final List<User> list) {
        for (User user : list) {
            userRepository.save(user);
        }
    }

}
