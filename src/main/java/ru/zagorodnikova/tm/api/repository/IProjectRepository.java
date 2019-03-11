package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectRepository<T extends AbstractEntity> {

    T persist(T t);
    void remove(T t);
    void removeAll(T t);
    T findOne(T t);
    void merge(T t);
    List<T> findAll(T t);

}
