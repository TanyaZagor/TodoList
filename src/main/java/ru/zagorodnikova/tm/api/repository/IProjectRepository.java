package ru.zagorodnikova.tm.api.repository;

import ru.zagorodnikova.tm.Entity.Project;

import java.util.Map;

public interface IProjectRepository {

    String addProject(Integer projectId, String projectName, String description, String dateStart, String dateFinish);
    String deleteProject(Integer projectId);
    void deleteAll();
    Map<Integer, Project> print();

}
