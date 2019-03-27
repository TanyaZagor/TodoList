package ru.zagorodnikova.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.ProjectRepository;
import ru.zagorodnikova.tm.api.repository.TaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjectService implements IProjectService {

    @NotNull private final SqlSession sqlSession;
    @NotNull private final ProjectRepository projectRepository;
    @NotNull private final TaskRepository taskRepository;

    public ProjectService(@NotNull final ServiceLocator serviceLocator) {
        this.sqlSession = serviceLocator.getSessionFactory().openSession();
        this.projectRepository = sqlSession.getMapper(ProjectRepository.class);
        this.taskRepository = sqlSession.getMapper(TaskRepository.class);
    }

    @Nullable
    public Project persistProject(@NotNull final String userId, @NotNull final String projectName,
                                  @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return null;
        if (description.isEmpty()) return null;
        if (dateStart.isEmpty()) return null;
        if (dateFinish.isEmpty()) return null;
        @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
        @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
        @NotNull final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setUserId(userId);
        newProject.setDescription(description);
        newProject.setUserId(userId);
        newProject.setDateStart(start);
        newProject.setDateFinish(finish);
        projectRepository.persist(newProject);
        sqlSession.commit();
        return newProject;
    }

    public void removeProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(userId, projectName);
        if (project != null) {
            taskRepository.removeAllInProject(project.getId());
            projectRepository.remove(project.getId());
            sqlSession.commit();
        }
    }

    public void removeAllProjects(@NotNull final String userId){
        taskRepository.removeAll(userId);
        projectRepository.removeAll(userId);
        sqlSession.commit();
    }

    @Nullable
    public List<Project> findAllProjects(@NotNull final String userId) {
        return projectRepository.findAll(userId);
    }

    @Nullable
    public Project findOneProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        return projectRepository.findOne(userId, projectName);
    }

    public void mergeProject(@NotNull final String userId,
                             @NotNull final String oldProjectName,
                             @NotNull final String projectName,
                             @NotNull final String description,
                             @NotNull final String dateStart,
                             @NotNull final String dateFinish,
                             @NotNull final String status) throws Exception {
        if (oldProjectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(userId, oldProjectName);
        if (project != null) {
            if (projectName.isEmpty()) return;
            if (description.isEmpty()) return;
            if (dateStart.isEmpty()) return;
            if (dateFinish.isEmpty()) return;
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            @NotNull final Status newStatus = createStatus(status);
            projectRepository.merge(project.getId(), projectName, description, start, finish, newStatus);
            sqlSession.commit();
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
        return projectRepository.getProjects();
    }

    public void setProjects(@NotNull final List<Project> list) {
        for (Project v : list) {
            projectRepository.persist(v);
        }
        sqlSession.commit();
    }

    private Status createStatus(String status) {
        switch (status) {
            case "scheduled": return Status.SCHEDULED;
            case "in progress": return Status.IN_PROGRESS;
            case "done" : return Status.DONE;
            default: return Status.SCHEDULED;
        }
    }

}
