package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;

import java.util.*;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository<Task> {

    @NotNull private final Map<String, Task> tasks = new LinkedHashMap<>();

    @Nullable
    public Task persist(@NotNull final Task task) {
        if (!tasks.containsValue(task)) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public void remove(@NotNull final Task task) {
        tasks.entrySet().removeIf((v) -> (Objects.equals(v.getValue().getProjectId(), task.getProjectId()) && Objects.equals(v.getValue().getName(), task.getName())));
    }

    public void merge(@NotNull final Task task) {
        tasks.get(task.getId()).setName(task.getName());
        tasks.get(task.getId()).setDescription(task.getDescription());
        tasks.get(task.getId()).setDateStart(task.getDateStart());
        tasks.get(task.getId()).setDateFinish(task.getDateFinish());

    }


    public void removeAll(@NotNull final Task task) {
        tasks.entrySet().removeIf((v) -> Objects.equals(v.getValue().getUserId(), task.getUserId()));
    }

    public void removeAllInProject(@NotNull final Task task) {
        tasks.entrySet().removeIf((v) -> Objects.equals(v.getValue().getProjectId(), task.getProjectId()));
    }

    @Nullable
    public List<Task> findAll(@NotNull final Task task) {
        final List<Task> list = new ArrayList<>();
        tasks.forEach((k, v) -> {
            if(Objects.equals(v.getProjectId(), task.getProjectId())) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Nullable
    public List<Task> findAllTasks(@NotNull final Task task) {
        final List<Task> list = new ArrayList<>();
        tasks.forEach((k, v) -> {
            if(Objects.equals(v.getUserId(), task.getUserId())) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Nullable
    public Task findOne(@NotNull final Task task) {
        final List<Task> list = new ArrayList<>();
        if (task.getDescription() != null && !task.getDescription().isEmpty()) {
            tasks.forEach((k, v) -> {
                if (Objects.equals(v.getProjectId(), task.getProjectId()) && Objects.requireNonNull(v.getDescription()).contains(task.getDescription())) {
                    list.add(v);
                }
            });
        }
        if (task.getName() != null && !task.getName().isEmpty()) {
            tasks.forEach((k, v) -> {
                if (Objects.equals(v.getProjectId(), task.getProjectId()) && Objects.requireNonNull(v.getName()).contains(task.getName())) {
                    list.add(v);
                }
            });
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    @NotNull
    public List<Task> sortByDateCreated(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> (o2).getDateCreate().compareTo((o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Task> sortByDateStart(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateStart()).compareTo(Objects.requireNonNull(( o1).getDateStart()))));
        Collections.reverse(list);
        return list;
    }
    @NotNull
    public List<Task> sortByDateFinish(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(o2.getDateFinish()).compareTo(Objects.requireNonNull(o1.getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Task> sortByStatus(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> o2.getStatus().compareTo(o1.getStatus())));
        Collections.reverse(list);
        return list;
    }

}
