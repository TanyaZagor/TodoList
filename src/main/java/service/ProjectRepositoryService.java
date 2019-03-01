package service;

import Repository.ProjectRepository;

public class ProjectRepositoryService {


    private ProjectRepository projectRepository;
    private String result;


    public String  addProject(String projectId, String projectName, String description) {
        result = null;
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        Integer pId = null;
        try {
            pId = Integer.valueOf(projectId);
            projectRepository.addProject(pId, projectName, description);
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
            projectRepository.deleteProject(pId);
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

    public void print() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        projectRepository.print();
    }

}
