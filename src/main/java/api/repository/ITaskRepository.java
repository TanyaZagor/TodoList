package api.repository;

import java.util.Date;

public interface ITaskRepository {

    void addTask(Integer projectId, Integer taskId, String taskName, String description, Date Date);
    void deleteTask(Integer projectId, Integer taskId);
    void deleteAll(Integer projectId);
    void print(Integer projectId);

}
