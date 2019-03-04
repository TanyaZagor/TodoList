package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.Entity.Project;

import java.util.Map;

public interface IProjectRepository {

    void persist(String projectName, String description, String dateStart, String dateFinish);
    void remove(String projectName);
    void removeAll();
    void merge(String oldProjectName, String projectName, String description, String dateStart, String dateFinish);
    Map<String, Project> print();

}
