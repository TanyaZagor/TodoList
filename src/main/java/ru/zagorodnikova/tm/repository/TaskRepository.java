package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository extends AbstractRepository<AbstractEntity> implements ITaskRepository<AbstractEntity> {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public AbstractEntity persist(AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        if (!tasks.containsValue(task)) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public void remove(AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        tasks.entrySet().removeIf((v) -> (v.getValue().getProjectId().equals(task.getProjectId()) && v.getValue().getName().equals(task.getName())));
    }

    public void merge(AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        tasks.get(task.getId()).setName(task.getName());
        tasks.get(task.getId()).setDescription(task.getDescription());
        tasks.get(task.getId()).setDateStart(task.getDateStart());
        tasks.get(task.getId()).setDateFinish(task.getDateFinish());

    }


    public void removeAll(AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        tasks.entrySet().removeIf((v) -> v.getValue().getUserId().equals(task.getUserId()));
    }

    public void removeAllInProject(AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        tasks.entrySet().removeIf((v) -> v.getValue().getProjectId().equals(task.getProjectId()));
    }

    public List<AbstractEntity> findAll(AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        final List<AbstractEntity> list = new ArrayList<>();
        tasks.forEach((k, v) -> {
            if(v.getProjectId().equals(task.getProjectId())) {
                list.add(v);
            }
        });

        return list;

    }

    public AbstractEntity findOne(AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        final List<Task> list = new ArrayList<>();
        tasks.forEach((k, v) -> {
            if(v.getProjectId().equals(task.getProjectId()) && v.getName().equals(task.getName())) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
