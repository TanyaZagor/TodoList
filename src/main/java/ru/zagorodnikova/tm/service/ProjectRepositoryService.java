package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.Entity.Project;
import ru.zagorodnikova.tm.Repository.ProjectRepository;

import java.util.Map;

public class ProjectRepositoryService {


    private ProjectRepository projectRepository;
    private String result;
    private Integer pId = null;

    public ProjectRepositoryService() {
        projectRepository = new ProjectRepository();
    }

    public String  addProject(String projectId, String projectName, String description, String dateFinish) {
        result = null;
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        if (projectName.length() == 0 || description.length() == 0 || dateFinish.length() == 0) {
            return "Not enough data";
        }
        try {
            pId = Integer.valueOf(projectId);
            result = projectRepository.addProject(pId, projectName, description, dateFinish);
        } catch (NumberFormatException e) {
            result = "Wrong id";
        }
        return result;
    }

    public String deleteProject( String projectId) {
        result = null;
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {
            Integer pId = Integer.valueOf(projectId);
            result = projectRepository.deleteProject(pId);
        } catch (NumberFormatException e) {
            result = "Wrong id";
        }
        return result;
    }

    public void deleteAll() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        projectRepository.deleteAll();
    }

    public Map<Integer, Project> print() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository.print();
    }

    public String updateProject(String projectId, String updateId, String newData) {
        result = null;
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {
            Integer update = Integer.valueOf(updateId);
            pId = Integer.valueOf(projectId);
            if (newData.length() == 0) {
                return "not enough data";
            }
            result = projectRepository.updateProject(pId, update, newData);
        } catch (NumberFormatException e) {
            result = "wrong update id";
        }
        return result;
    }
}
