package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectRepository {

    Project persist(String userId, String projectName, String description, String dateStart, String dateFinish);
    void remove(String projectId);
    void removeAll(String userId);
    Project findOne(String userId, String projectName);
    void merge(String projectId, String projectName, String description, String dateStart, String dateFinish);
    List<Project> findAll(String userId);

}
