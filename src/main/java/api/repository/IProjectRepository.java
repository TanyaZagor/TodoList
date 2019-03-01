package api.repository;

public interface IProjectRepository {

    void addProject(Integer projectId, String projectName, String description);
    void deleteProject(Integer projectId);
    void deleteAll();
    void print();
}
