package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signIn(String login, String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        return userRepository.signIn(login, password);
    }

    public User signUp(String login, String password, String fistName, String lastName, String email, RoleType roleType) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        if (fistName == null || fistName.isEmpty()) return null;
        if (lastName == null || lastName.isEmpty()) return null;
        if (email == null || email.isEmpty()) return null;

        return userRepository.signUp(login, password, fistName, lastName, email, roleType);
    }

    public void changePassword(String userId, String login, String oldPassword, String newPassword) {
        if (login == null || login.isEmpty()) return;
        if (oldPassword == null || oldPassword.isEmpty()) return;
        if (newPassword == null || newPassword.isEmpty()) return;
        userRepository.changePassword(userId,login, oldPassword, newPassword);
    }

    public void update(String userId, String firstName, String lastName, String email) {
        if (firstName == null || firstName.isEmpty()) return;
        if (lastName == null || lastName.isEmpty()) return;
        if (email == null || email.isEmpty()) return;
        userRepository.update(userId, firstName, lastName, email);
    }
}
