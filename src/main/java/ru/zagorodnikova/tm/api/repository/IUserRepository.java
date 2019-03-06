package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

public interface IUserRepository {

    User signUp(String login, String password, String firstName, String lastName, String email, RoleType roleType);
    User signIn(String login, String password);
    void changePassword(String userId, String login, String oldPassword, String newPassword);
    void update(String userId, String firstName, String lastName, String email);
}
