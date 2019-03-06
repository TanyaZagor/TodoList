package ru.zagorodnikova.tm.api.service;

import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

public interface IUserService {
    User signIn(String login, String password);
    User signUp(String login, String password, String fistName, String lastName, String email, RoleType roleType);
    void changePassword(String userId, String login, String oldPassword, String newPassword);
    void update(String userId, String firstName, String lastName, String email);
}
