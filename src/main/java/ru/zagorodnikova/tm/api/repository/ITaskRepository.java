package ru.zagorodnikova.tm.api.repository;


import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;


public interface ITaskRepository<T extends AbstractEntity> {

    T persist(T t);
    void remove(T t);
    void removeAll(T t);
    void removeAllInProject(T t);
    T findOne(T t);
    void merge(T t);
    List<T> findAll(T t);

}
