package ru.zagorodnikova.tm.repository;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.mapper.ITaskMapper;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.FieldConst;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository<Task> {

    @NotNull private final SqlSession sqlSession;
    @NotNull private final ITaskMapper taskMapper;

    public TaskRepository(ServiceLocator serviceLocator) {
        sqlSession = serviceLocator.getSessionFactory().openSession();
        this.taskMapper = sqlSession.getMapper(ITaskMapper.class);
    }

    @Nullable
    @Override
    public Task persist(@NotNull final Task task) {
        taskMapper.persist(task);
        sqlSession.commit();
        return task;
    }

    @Override
    public void remove(@NotNull final String id) {
        taskMapper.remove(id);
        sqlSession.commit();
    }

    public void merge(@NotNull final String id,
                      @NotNull final String name,
                      @NotNull final String description,
                      @NotNull final Date dateStart,
                      @NotNull final Date dateFinish,
                      @NotNull final String  status){
        taskMapper.merge(id, name, description, dateStart, dateFinish, status);
        sqlSession.commit();
    }


    public void removeAll(@NotNull final String userId) {
        taskMapper.removeAll(userId);
        sqlSession.commit();
    }

    public void removeAllInProject(@NotNull final String projectId) {
        taskMapper.removeAllInProject(projectId);
        sqlSession.commit();
    }

    @Nullable
    @SneakyThrows
    public Task findOne(@NotNull final String projectId, @NotNull final String name) {
        return taskMapper.findOne(projectId, name);
    }


    @NotNull
    public List<Task> sortByDateCreated(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> o2.getDateCreate().compareTo(o1.getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Task> sortByDateStart(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(o2.getDateStart()).compareTo(Objects.requireNonNull(o1.getDateStart()))));
        Collections.reverse(list);
        return list;
    }


    @NotNull
    public List<Task> sortByDateFinish(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(o2.getDateFinish()).compareTo(Objects.requireNonNull(o1.getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Task> sortByStatus(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> o2.getStatus().compareTo(o1.getStatus())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    @SneakyThrows
    public List<Task> getTasks() {
        return taskMapper.getTasks();
    }

    public void setTasks(@NotNull final List<Task> list){
        for (Task task : list) {
            persist(task);
        }
    }

    @SneakyThrows
    public List<Task> findAllTasksInProject(@NotNull final String projectId) {
        return taskMapper.findAllTasksInProject(projectId);
    }

    @SneakyThrows
    public List<Task> findAllTasks(@NotNull final String userId) {
        return taskMapper.findAllTasks(userId);
    }
}
