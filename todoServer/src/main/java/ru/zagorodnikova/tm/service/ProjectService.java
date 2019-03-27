package ru.zagorodnikova.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjectService implements IProjectService {

    @NotNull private final SqlSessionFactory sqlSessionFactory;

    public ProjectService(@NotNull final ServiceLocator serviceLocator) {
        this.sqlSessionFactory = serviceLocator.getSessionFactory();
    }

    @Nullable
    public Project persistProject(@NotNull final String userId, @NotNull final String projectName,
                                  @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) {
        if (projectName.isEmpty()) return null;
        if (description.isEmpty()) return null;
        if (dateStart.isEmpty()) return null;
        if (dateFinish.isEmpty()) return null;
        try(SqlSession sqlSession = getSqlSession()) {
            try{
                @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
                @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
                @NotNull final Project newProject = new Project();
                newProject.setName(projectName);
                newProject.setUserId(userId);
                newProject.setDescription(description);
                newProject.setUserId(userId);
                newProject.setDateStart(start);
                newProject.setDateFinish(finish);
                ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
                projectRepository.persist(newProject);
                sqlSession.commit();
                return newProject;
            } catch (Exception e) {
                sqlSession.rollback();
                return null;
            }
        }

    }

    public void removeProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return;
        try(SqlSession sqlSession = getSqlSession()) {
            try {
                ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
                TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
                @Nullable final Project project = projectRepository.findOne(userId, projectName);
                if (project != null) {
                    taskRepository.removeAllInProject(project.getId());
                    projectRepository.remove(project.getId());
                    sqlSession.commit();
                }
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }

    public void removeAllProjects(@NotNull final String userId){
        try(SqlSession sqlSession = getSqlSession()) {
            try {
                ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
                TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
                taskRepository.removeAll(userId);
                projectRepository.removeAll(userId);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }

    @Nullable
    public List<Project> findAllProjects(@NotNull final String userId) {
        try(SqlSession sqlSession = getSqlSession()) {
            ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            return projectRepository.findAll(userId);
        }
    }

    @Nullable
    public Project findOneProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        try(SqlSession sqlSession = getSqlSession()) {
            ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            return projectRepository.findOne(userId, projectName);
        }
    }

    public void mergeProject(@NotNull final String userId,
                             @NotNull final String oldProjectName,
                             @NotNull final String projectName,
                             @NotNull final String description,
                             @NotNull final String dateStart,
                             @NotNull final String dateFinish,
                             @NotNull final String status) {
        if (oldProjectName.isEmpty()) return;
        if (projectName.isEmpty()) return;
        if (description.isEmpty()) return;
        if (dateStart.isEmpty()) return;
        if (dateFinish.isEmpty()) return;
        try(SqlSession sqlSession = getSqlSession()) {
            try {
                ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
                @Nullable final Project project = projectRepository.findOne(userId, oldProjectName);
                if (project != null) {
                    @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
                    @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
                    @NotNull final Status newStatus = createStatus(status);
                    projectRepository.merge(project.getId(), projectName, description, start, finish, newStatus);
                    sqlSession.commit();
                }
            } catch (Exception e) {
                sqlSession.rollback();
            }

        }
    }
    @Nullable
    public List<Project> sortProjectsByDateCreated(@NotNull final String userId) {
        List<Project> list = findAllProjects(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> (o2).getDateCreate().compareTo((o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @Nullable
    public List<Project> sortProjectsByDateStart(@NotNull final String userId){
        List<Project> list = findAllProjects(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateStart())
                .compareTo(Objects.requireNonNull((o1).getDateStart()))));
        Collections.reverse(list);
        return list;
    }
    @Nullable
    public List<Project> sortProjectsByDateFinish(@NotNull final String userId){
        List<Project> list = findAllProjects(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateFinish())
                .compareTo(Objects.requireNonNull((o1).getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @Nullable
    public List<Project> sortProjectsByStatus(@NotNull final String userId) {
        List<Project> list = findAllProjects(userId);
        if (list == null || list.isEmpty()) return null;
        list.sort(((o1, o2) -> (o2).getStatus().compareTo((o1).getStatus())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Project> getProjects() {
        try(SqlSession sqlSession = getSqlSession()) {
            ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            return projectRepository.getProjects();
        }
    }

    public void setProjects(@NotNull final List<Project> list) {
        try(SqlSession sqlSession = getSqlSession()) {
            try {
                ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
                for (Project v : list) {
                    projectRepository.persist(v);
                }
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }

    private Status createStatus(String status) {
        switch (status) {
            case "scheduled": return Status.SCHEDULED;
            case "in progress": return Status.IN_PROGRESS;
            case "done" : return Status.DONE;
            default: return Status.SCHEDULED;
        }
    }

    private SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
