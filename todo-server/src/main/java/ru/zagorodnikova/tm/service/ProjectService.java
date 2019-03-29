package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.repositoty.ProjectRepository;
import ru.zagorodnikova.tm.repositoty.TaskRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Inject
    private EntityManagerFactory factory;

    @Nullable
    public Project persistProject(@NotNull final String userId, @NotNull final String projectName,
                                  @NotNull final String description, @NotNull final String dateStart, @NotNull final String dateFinish) throws Exception {
        if (projectName.isEmpty()) return null;
        if (description.isEmpty()) return null;
        if (dateStart.isEmpty()) return null;
        if (dateFinish.isEmpty()) return null;
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            @NotNull final Project project = new Project(userId, projectName, description, start, finish);
            entityManager.getTransaction().begin();
            projectRepository.merge(project);
            entityManager.getTransaction().commit();
            return project;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public void removeProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return;
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            Project project = projectRepository.findOne(userId, projectName);
            if (project == null) return;
            entityManager.getTransaction().begin();
            projectRepository.remove(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeAllProjects(@NotNull final String userId){
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            entityManager.getTransaction().begin();
            projectRepository.removeAll(userId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    public List<Project> findAllProjects(@NotNull final String userId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            return projectRepository.findAll(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    public Project findOneProject(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        try {
            EntityManager entityManager = factory.createEntityManager();
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            return projectRepository.findOne(userId, projectName);
        } catch (Exception e) {
            return null;
        }
    }

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
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            Project project = projectRepository.findOne(userId, oldProjectName);
            if (project == null) return;
            @NotNull final Date start = DateFormatterUtil.dateFormatter(dateStart);
            @NotNull final Date finish = DateFormatterUtil.dateFormatter(dateFinish);
            @NotNull final Status newStatus = createStatus(status);
            project.setName(projectName);
            project.setDescription(description);
            project.setDateStart(start);
            project.setDateFinish(finish);
            project.setStatus(newStatus);
            entityManager.getTransaction().begin();
            projectRepository.merge(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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

    @Nullable
    public List<Project> getProjects() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            return projectRepository.getProjects();
        } catch (Exception e) {
            return null;
        }
    }

    public void setProjects(@NotNull final List<Project> list) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            ProjectRepository projectRepository = new ProjectRepository(entityManager);
            entityManager.getTransaction().begin();
            for (Project v : list) {
                projectRepository.persist(v);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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

}
