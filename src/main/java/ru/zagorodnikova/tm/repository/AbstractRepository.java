package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractRepository {

    abstract public AbstractEntity persist(AbstractEntity abstractEntity);

    abstract public void remove(AbstractEntity abstractEntity);

    abstract public void removeAll(AbstractEntity abstractEntity);

    abstract public AbstractEntity findOne(AbstractEntity abstractEntity);
    abstract public void merge(AbstractEntity abstractEntity);
    abstract public List<AbstractEntity> findAll(AbstractEntity abstractEntity);
}
