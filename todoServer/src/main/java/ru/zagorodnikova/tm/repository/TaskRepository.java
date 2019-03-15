package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Task;

import java.util.*;

public class TaskRepository extends AbstractRepository<AbstractEntity> implements ITaskRepository<AbstractEntity> {

    @NotNull private final Map<String, Task> tasks = new LinkedHashMap<>();

    @Nullable
    public AbstractEntity persist(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
        if (!tasks.containsValue(task)) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public void remove(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
        tasks.entrySet().removeIf((v) -> (Objects.equals(v.getValue().getProjectId(), task.getProjectId()) && Objects.equals(v.getValue().getName(), task.getName())));
    }

    public void merge(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
        tasks.get(task.getId()).setName(task.getName());
        tasks.get(task.getId()).setDescription(task.getDescription());
        tasks.get(task.getId()).setDateStart(task.getDateStart());
        tasks.get(task.getId()).setDateFinish(task.getDateFinish());

    }


    public void removeAll(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
        tasks.entrySet().removeIf((v) -> Objects.equals(v.getValue().getUserId(), task.getUserId()));
    }

    public void removeAllInProject(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
        tasks.entrySet().removeIf((v) -> Objects.equals(v.getValue().getProjectId(), task.getProjectId()));
    }

    @Nullable
    public List<AbstractEntity> findAll(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
        final List<AbstractEntity> list = new ArrayList<>();
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
    public List<AbstractEntity> findAllTasks(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
        final List<AbstractEntity> list = new ArrayList<>();
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
    public AbstractEntity findOne(@NotNull final AbstractEntity abstractEntity) {
        @NotNull final Task task = (Task) abstractEntity;
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
    public List<AbstractEntity> sortByDateCreated(@NotNull final List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Task)o2).getDateCreate().compareTo(((Task)o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<AbstractEntity> sortByDateStart(@NotNull final List<AbstractEntity> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(((Task) o2).getDateStart()).compareTo(Objects.requireNonNull(((Task) o1).getDateStart()))));
        Collections.reverse(list);
        return list;
    }
    @NotNull
    public List<AbstractEntity> sortByDateFinish(@NotNull final List<AbstractEntity> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(((Task) o2).getDateFinish()).compareTo(Objects.requireNonNull(((Task) o1).getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<AbstractEntity> sortByStatus(@NotNull final List<AbstractEntity> list) {
        list.sort(((o1, o2) -> ((Task)o2).getStatus().compareTo(((Task)o1).getStatus())));
        Collections.reverse(list);
        return list;
    }

}
