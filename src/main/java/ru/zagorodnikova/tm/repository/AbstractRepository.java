package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> {

    abstract public T persist(T t);

    abstract public void remove(T t);

    abstract public void removeAll(T t);

    abstract public T findOne(T t);
    abstract public void merge(T t);
    abstract public List<T> findAll(T t);
}
