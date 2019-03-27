package ru.zagorodnikova.tm.repository;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.mapper.IProjectMapper;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.enumeration.FieldConst;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository<Project>{

    @NotNull private final SqlSession sqlSession;
    @NotNull private final IProjectMapper projectMapper;


    public ProjectRepository(ServiceLocator serviceLocator) {
        this.sqlSession = serviceLocator.getSessionFactory().openSession();
        this.projectMapper = sqlSession.getMapper(IProjectMapper.class);
    }

    @Override
    public void remove(@NotNull String id){
        projectMapper.remove(id);
        sqlSession.commit();
    }

    public void removeAll(@NotNull final String userId) {
        projectMapper.removeAll(userId);
        sqlSession.commit();
    }

    @NotNull
    @SneakyThrows
    public List<Project> getProjects() {
        return projectMapper.getProjects();
    }

    public void setProjects(@NotNull final List<Project> list) {
        for (Project v : list) {
            persist(v);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public Project persist(@NotNull Project project) {
        projectMapper.persist(project);
        sqlSession.commit();
        return project;
    }

    @Nullable
    @SneakyThrows
    public Project findOne(@NotNull final String userId, @NotNull final String name){
        return projectMapper.findOne(userId, name);
    }

    @SneakyThrows
    public void merge(@NotNull final String id,
                      @NotNull final String projectName,
                      @NotNull final String description,
                      @NotNull final Date dateStart,
                      @NotNull final Date dateFinish,
                      @NotNull final Status status) {
        projectMapper.merge(id, projectName, description, dateStart, dateFinish, status);
        sqlSession.commit();
    }

    @Nullable
    public List<Project> sortByDateCreated(@NotNull final String userId) {
        List<Project> list = findAll(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> (o2).getDateCreate().compareTo((o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @Nullable
    public List<Project> sortByDateStart(@NotNull final String userId) {
        List<Project> list = findAll(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateStart())
                .compareTo(Objects.requireNonNull((o1).getDateStart()))));
        Collections.reverse(list);
        return list;
    }
    @Nullable
    public List<Project> sortByDateFinish(@NotNull final String userId) {
        List<Project> list = findAll(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateFinish())
                .compareTo(Objects.requireNonNull((o1).getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @Nullable
    public List<Project> sortByStatus(@NotNull final String userId) {
        List<Project> list = findAll(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> (o2).getStatus().compareTo((o1).getStatus())));
        Collections.reverse(list);
        return list;
    }

    @SneakyThrows
    public List<Project> findAll(@NotNull String userId) {
        return projectMapper.findAll(userId);
    }

}
