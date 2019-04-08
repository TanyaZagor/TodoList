package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.repositoty.ProjectRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Nullable
    @Transactional
    public Project persistProject(@NotNull final String userId, @NotNull final String projectName,
                                  @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return null;
        if (description.isEmpty()) return null;
        if (dateStart.isEmpty()) return null;
        if (dateFinish.isEmpty()) return null;
        if (findOneProject(userId, projectName) != null) return null;
        @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
        @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
        @NotNull final Project project = new Project(userId, projectName, description, start, finish);
        projectRepository.save(project);
        return project;
    }

    @Transactional
    public void removeProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return;
        Project project = findOneProject(userId, projectName);
        if (project == null) return;
        projectRepository.delete(project);
    }

    @Transactional
    public void removeAllProjects(@NotNull final String userId){
        projectRepository.removeAll(userId);
    }

    @Nullable
    @Transactional
    public List<Project> findAllProjects(@NotNull final String userId) {
        try {
            return projectRepository.findAllByUserId(userId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Transactional
    public Project findOneProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        try {
            return projectRepository.findOne(userId, projectName);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void mergeProject(@NotNull final String userId,
                             @NotNull final String oldProjectName,
                             @NotNull final String projectName,
                             @NotNull final String description,
                             @NotNull final String dateStart,
                             @NotNull final String dateFinish,
                             @NotNull final String status) throws Exception {
        if (oldProjectName.isEmpty()) return;
        if (projectName.isEmpty()) return;
        if (description.isEmpty()) return;
        if (dateStart.isEmpty()) return;
        if (dateFinish.isEmpty()) return;
        Project project = findOneProject(userId, oldProjectName);
        if (project == null) return;
        @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
        @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
        @NotNull final Status newStatus = Status.createStatus(status);
        project.setName(projectName);
        project.setDescription(description);
        project.setDateStart(start);
        project.setDateFinish(finish);
        project.setStatus(newStatus);
        projectRepository.save(project);
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

    @Nullable
    @Transactional
    public List<Project> getProjects() {
        try {
            return projectRepository.findAll();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void setProjects(@NotNull final List<Project> list) {
        for (Project v : list) {
            projectRepository.save(v);
        }
    }

}
