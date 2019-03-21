package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;

import java.util.*;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository<Project> {

    @NotNull private final Map<String, Project> projects = super.getMap();

    public void removeAll(@NotNull final Project project) {
        projects.entrySet().removeIf((v) -> Objects.equals(v.getValue().getUserId(), project.getUserId()));
    }

    @Nullable
    public List<Project> findAll(@NotNull final Project project) {
        @NotNull final List<Project> list = new ArrayList<>();
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

    @NotNull
    public List<Project> getProjects() {
        @NotNull final List<Project> list = new ArrayList<>();
        projects.forEach((k, v) -> list.add(v));
        return list;
    }

    public void setProjects(@NotNull final List<Project> list) {
        projects.clear();
        list.forEach((v) -> projects.put(v.getId(), v));
    }

    @Nullable
    public Project findOne(@NotNull final Project project){
        @NotNull final List<Project> list = new ArrayList<>();
        if (project.getDescription() != null && !project.getDescription().isEmpty()) {
            projects.forEach((k, v) -> {
                if (Objects.equals(v.getDescription(), project.getDescription()) && Objects.equals(v.getUserId(), project.getUserId())) {
                    list.add(v);
                }
            });
        }
        if (project.getName() != null && !project.getName().isEmpty()) {
            projects.forEach((k, v) -> {
                if (Objects.equals(v.getName(), project.getName()) && Objects.equals(v.getUserId(), project.getUserId())) {
                    list.add(v);
                }
            });
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void merge(@NotNull final Project project) {
        projects.get(project.getId()).setName(project.getName());
        projects.get(project.getId()).setDescription(project.getDescription());
        projects.get(project.getId()).setDateStart(project.getDateStart());
        projects.get(project.getId()).setDateFinish(project.getDateFinish());
    }

    @NotNull
    public List<Project> sortByDateCreated(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> (o2).getDateCreate().compareTo((o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Project> sortByDateStart(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateStart()).compareTo(Objects.requireNonNull((o1).getDateStart()))));
        Collections.reverse(list);
        return list;
    }
    @NotNull
    public List<Project> sortByDateFinish(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateFinish()).compareTo(Objects.requireNonNull((o1).getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Project> sortByStatus(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> (o2).getStatus().compareTo((o1).getStatus())));
        Collections.reverse(list);
        return list;
    }


}
