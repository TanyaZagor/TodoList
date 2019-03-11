package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        projects.forEach((k, v) -> {
            if (v.getName().equals(project.getName()) && v.getUserId().equals(project.getUserId())) {
                list.add(v);
            }
        });
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

}
