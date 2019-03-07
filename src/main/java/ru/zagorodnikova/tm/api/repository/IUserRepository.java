package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    User signUp(String login, String password, String firstName, String lastName, String email, RoleType roleType);
    User signIn(String login, String password);
    void changePassword(String userId, String login, String oldPassword, String newPassword);
    void update(String userId, String firstName, String lastName, String email);
    AbstractEntity persist(AbstractEntity abstractEntity);
    void remove(AbstractEntity abstractEntity);
    void removeAll(AbstractEntity abstractEntity);
    AbstractEntity findOne(AbstractEntity abstractEntity);
    void merge(AbstractEntity abstractEntity);
    List<AbstractEntity> findAll(AbstractEntity abstractEntity);
}
