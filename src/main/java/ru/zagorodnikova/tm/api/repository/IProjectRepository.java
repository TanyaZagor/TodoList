package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectRepository {

    AbstractEntity persist(AbstractEntity project);
    void remove(AbstractEntity abstractEntity);
    void removeAll(AbstractEntity abstractEntity);
    AbstractEntity findOne(AbstractEntity abstractEntity);
    void merge(AbstractEntity abstractEntity);
    List<AbstractEntity> findAll(AbstractEntity abstractEntity);

}
