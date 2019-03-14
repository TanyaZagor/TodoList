package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;

import java.util.*;

public class ProjectRepository extends AbstractRepository<AbstractEntity> implements IProjectRepository<AbstractEntity> {

    @NotNull private final Map<String, Project> projects = new LinkedHashMap<>();

    @Nullable
    public AbstractEntity persist(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        projects.put(project.getId(), project);
        return project;
    }

    public void remove(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        projects.remove(project.getId());
    }

    public void removeAll(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        projects.entrySet().removeIf((v) -> Objects.equals(v.getValue().getUserId(), project.getUserId()));
    }

    @Nullable
    public List<AbstractEntity> findAll(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        final List<AbstractEntity> list = new ArrayList<>();
        projects.forEach((k, v) -> {
            if (Objects.equals(v.getUserId(), project.getUserId())) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Nullable
    public AbstractEntity findOne(@NotNull AbstractEntity abstractEntity){
        @NotNull final Project project = (Project) abstractEntity;
        final List<Project> list = new ArrayList<>();
        if (project.getDescription() != null && !project.getDescription().isEmpty()) {
            projects.forEach((k, v) -> {
                if (Objects.requireNonNull(v.getDescription()).contains(project.getDescription()) && Objects.equals(v.getUserId(), project.getUserId())) {
                    list.add(v);
                }
            });
        }
        if (project.getName() != null && !project.getName().isEmpty()) {
            projects.forEach((k, v) -> {
                if (Objects.requireNonNull(v.getName()).contains(project.getName()) && Objects.equals(v.getUserId(), project.getUserId())) {
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
        @NotNull final Project project = (Project) abstractEntity;
        projects.get(project.getId()).setName(project.getName());
        projects.get(project.getId()).setDescription(project.getDescription());
        projects.get(project.getId()).setDateStart(project.getDateStart());
        projects.get(project.getId()).setDateFinish(project.getDateFinish());
    }

    @NotNull
    public List<AbstractEntity> sortByDateCreated(@NotNull List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Project)o2).getDateCreate().compareTo(((Project)o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<AbstractEntity> sortByDateStart(@NotNull List<AbstractEntity> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(((Project) o2).getDateStart()).compareTo(Objects.requireNonNull(((Project) o1).getDateStart()))));
        Collections.reverse(list);
        return list;
    }
    @NotNull
    public List<AbstractEntity> sortByDateFinish(@NotNull List<AbstractEntity> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(((Project) o2).getDateFinish()).compareTo(Objects.requireNonNull(((Project) o1).getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<AbstractEntity> sortByStatus(@NotNull List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Project)o2).getStatus().compareTo(((Project)o1).getStatus())));
        Collections.reverse(list);
        return list;
    }


}
