package ru.zagorodnikova.tm.api.service;

import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;

public interface IUserService {
    AbstractEntity signIn(String login, String password);
    AbstractEntity signUp(String login, String password, String fistName, String lastName, String email, RoleType roleType);
    void changePassword(String userId, String login, String oldPassword, String newPassword);
    void update(String userId, String firstName, String lastName, String email);
}
