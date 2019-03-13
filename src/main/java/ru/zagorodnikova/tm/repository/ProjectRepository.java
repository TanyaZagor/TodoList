package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;

import java.util.*;

public class ProjectRepository extends AbstractRepository<AbstractEntity> implements IProjectRepository<AbstractEntity> {

    private final Map<String, Project> projects = new LinkedHashMap<>();

    @Nullable
    public AbstractEntity persist(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.put(project.getId(), project);
        return project;
    }

    public void remove(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.remove(project.getId());
    }

    public void removeAll(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.entrySet().removeIf((v) -> v.getValue().getUserId().equals(project.getUserId()));
    }

    @Nullable
    public List<AbstractEntity> findAll(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        final List<AbstractEntity> list = new ArrayList<>();
        projects.forEach((k, v) -> {
            if (v.getUserId().equals(project.getUserId())) {
                list.add(v);
            }
        });
        return list;
    }

    @Nullable
    public AbstractEntity findOne(@NotNull AbstractEntity abstractEntity){
        Project project = (Project) abstractEntity;
        final List<Project> list = new ArrayList<>();
        if (project.getDescription() != null && !project.getDescription().isEmpty()) {
            projects.forEach((k, v) -> {
                if (v.getDescription().contains(project.getDescription()) && v.getUserId().equals(project.getUserId())) {
                    list.add(v);
                }
            });
        }
        if (project.getName() != null && !project.getName().isEmpty()) {
            projects.forEach((k, v) -> {
                if (v.getName().contains(project.getName()) && v.getUserId().equals(project.getUserId())) {
                    list.add(v);
                }
            });
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void merge(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.get(project.getId()).setName(project.getName());
        projects.get(project.getId()).setDescription(project.getDescription());
        projects.get(project.getId()).setDateStart(project.getDateStart());
        projects.get(project.getId()).setDateFinish(project.getDateFinish());
    }

    @Nullable
    public List<AbstractEntity> sortByDateCreated(List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Project)o2).getDateCreate().compareTo(((Project)o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @Nullable
    public List<AbstractEntity> sortByDateStart(List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Project)o2).getDateStart().compareTo(((Project)o1).getDateStart())));
        Collections.reverse(list);
        return list;
    }
    @Nullable
    public List<AbstractEntity> sortByDateFinish(List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Project)o2).getDateFinish().compareTo(((Project)o1).getDateFinish())));
        Collections.reverse(list);
        return list;
    }

    @Nullable
    public List<AbstractEntity> sortByStatus(List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Project)o2).getStatus().compareTo(((Project)o1).getStatus())));
        Collections.reverse(list);
        return list;
    }

}
