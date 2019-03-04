package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.Entity.Project;
import ru.zagorodnikova.tm.Repository.ProjectRepository;

import java.util.Map;

public class ProjectRepositoryService {


    private ProjectRepository projectRepository;
    private String result;

    public ProjectRepositoryService() {
        projectRepository = new ProjectRepository();
    }

    public String  addProject(String projectName, String description, String dateStart, String dateFinish) {
        result = null;
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        if (projectName.length() == 0 || description.length() == 0|| dateStart.length() == 0 || dateFinish.length() == 0) {
            return "Not enough data";
        }
        try {
            projectRepository.persist(projectName, description, dateStart, dateFinish);
        } catch (NumberFormatException e) {
            result = "Wrong id";
        }
        return result;
    }

    public String deleteProject( String projectName) {
        result = null;
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {
            projectRepository.remove(projectName);
        } catch (NumberFormatException e) {
            result = "Wrong id";
        }
        return result;
    }

    public void deleteAll() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        projectRepository.removeAll();
    }

    public Map<String, Project> print() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository.print();
    }

    public String updateProject(String oldProjectName, String projectName, String description, String dateStart, String dateFinish) {
        result = null;
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {
            projectRepository.merge(oldProjectName, projectName, description, dateStart, dateFinish);
        } catch (NumberFormatException e) {
            result = "wrong update id";
        }
        return result;
    }
}
