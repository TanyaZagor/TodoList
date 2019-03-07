package ru.zagorodnikova.tm.api.service;

import ru.zagorodnikova.tm.entity.AbstractEntity;
import java.util.List;

public interface ITaskService {
    AbstractEntity persist(String userId, String projectName, String taskName, String description, String dateStart, String dateFinish);
    void remove(String userId, String projectName, String taskName);
    void removeAllInProject(String userId, String projectName);
    void removeAll(String userId);
    void merge(String userId, String projectName, String oldTaskName, String taskName, String description, String dateStart, String dateFinish);
    List<AbstractEntity> findAll(String userId, String projectName);
    AbstractEntity findOne(String userId, String projectName, String taskName);
}
