package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public interface IUserRepository<T extends AbstractEntity> {

    T signIn(T t);
    void changePassword(T t);
    T persist(T t);
    void remove(T t);
    void removeAll(T t);
    T findOne(T t);
    void merge(T t);
    List<T> findAll(T t);
    boolean checkPassword(T t);
}
