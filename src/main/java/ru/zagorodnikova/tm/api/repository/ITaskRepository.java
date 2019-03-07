package ru.zagorodnikova.tm.api.repository;


import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;


public interface ITaskRepository {

    AbstractEntity persist(AbstractEntity abstractEntity);
    void remove(AbstractEntity abstractEntity);
    void removeAllInProject(AbstractEntity abstractEntity);
    void removeAll(AbstractEntity abstractEntity);
    void merge(AbstractEntity abstractEntity);
    List<AbstractEntity> findAll(AbstractEntity abstractEntity);
    AbstractEntity findOne(AbstractEntity abstractEntity);

}
