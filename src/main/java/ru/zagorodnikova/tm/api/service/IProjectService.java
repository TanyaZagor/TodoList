package ru.zagorodnikova.tm.api.service;

import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectService {

    Project persist(String userId, String projectName, String description, String dateStart, String dateFinish);
    void remove(String userId, String projectName);
    void removeAll(String userId);
    List<AbstractEntity> findAll(String userId);
    Project findOne(String userId, String projectName);
    void merge(String userId, String oldProjectName, String projectName, String description, String dateStart, String dateFinish);
}
