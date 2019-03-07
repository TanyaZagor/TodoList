package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    User signIn(AbstractEntity abstractEntity);
    void changePassword(AbstractEntity abstractEntity);
    AbstractEntity persist(AbstractEntity abstractEntity);
    void remove(AbstractEntity abstractEntity);
    void removeAll(AbstractEntity abstractEntity);
    AbstractEntity findOne(AbstractEntity abstractEntity);
    void merge(AbstractEntity abstractEntity);
    List<AbstractEntity> findAll(AbstractEntity abstractEntity);
    boolean checkPassword(AbstractEntity abstractEntity);
}
