package ru.zagorodnikova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.FieldConst;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository<Task> {

    @NotNull private final Map<String, Task> tasks = super.getMap();
    @NotNull private final Connection connection;

    public TaskRepository(ServiceLocator serviceLocator) throws Exception {
        this.connection = serviceLocator.getConnection();
    }

    @Nullable
    @Override
    public Task persist(@NotNull Task task) throws Exception {
        @NotNull final String query = "INSERT INTO todo_list.app_task (id, user_id, project_id, name, description, dateStart, dateFinish, dateCreate) \n" +
                " VALUES ('"+ task.getId()+"', '"+ task.getUserId() +"', '"+ task.getProjectId() +"', '"+ task.getName() +"', '"+ task.getDescription() +"', '"+ DateFormatterUtil.dateFormatter(task.getDateStart()) +"', '"+ DateFormatterUtil.dateFormatter(task.getDateFinish()) +"', '"+ DateFormatterUtil.dateFormatter(task.getDateCreate()) +"');";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        if (!tasks.containsValue(task)) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    @Override
    public void remove(@NotNull Task task) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_task " +
                "WHERE id = '"+ task.getId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        tasks.remove(task.getId());
    }

    public void merge(@NotNull final Task task) throws Exception {
        @NotNull final String query =  "UPDATE todo_list.app_task SET " +
                "name = '"+ task.getName() +"', " +
                "description = '"+ task.getDescription() +"', " +
                "dateStart = '"+ DateFormatterUtil.dateFormatter(task.getDateStart()) +"', " +
                "dateFinish = '"+ DateFormatterUtil.dateFormatter(task.getDateFinish()) +"'  " +
                "WHERE id = '"+ task.getId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        tasks.get(task.getId()).setName(task.getName());
        tasks.get(task.getId()).setDescription(task.getDescription());
        tasks.get(task.getId()).setDateStart(task.getDateStart());
        tasks.get(task.getId()).setDateFinish(task.getDateFinish());

    }


    public void removeAll(@NotNull final Task task) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_task " +
                "WHERE user_id = '"+ task.getUserId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        tasks.entrySet().removeIf((v) -> Objects.equals(v.getValue().getUserId(), task.getUserId()));
    }

    public void removeAllInProject(@NotNull final Task task) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_task " +
                "WHERE project_id = '"+ task.getProjectId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        tasks.entrySet().removeIf((v) -> Objects.equals(v.getValue().getProjectId(), task.getProjectId()));
    }

    @Nullable
    public Task findOne(@NotNull final Task task) {
        @NotNull final List<Task> list = new ArrayList<>();
        if (task.getDescription() != null && !task.getDescription().isEmpty()) {
            tasks.forEach((k, v) -> {
                if (Objects.equals(v.getProjectId(), task.getProjectId()) && Objects.equals(v.getDescription(), task.getDescription())) {
                    list.add(v);
                }
            });
        }
        if (task.getName() != null && !task.getName().isEmpty()) {
            tasks.forEach((k, v) -> {
                if (Objects.equals(v.getProjectId(), task.getProjectId()) && Objects.equals(v.getName(), task.getName())){
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
        list.sort(((o1, o2) -> o2.getDateCreate().compareTo(o1.getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Task> sortByDateStart(@NotNull final List<Task> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull(o2.getDateStart()).compareTo(Objects.requireNonNull(o1.getDateStart()))));
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

    @NotNull
    public List<Task> getTasks() {
        @NotNull final List<Task> list = new ArrayList<>();
        tasks.forEach((k, v) -> list.add(v));
        return list;
    }

    public void setTasks(@NotNull final List<Task> list) {
        tasks.clear();
        list.forEach((v) -> tasks.put(v.getId(), v));
    }

    @SneakyThrows
    public List<Task> findAllTasksInProject(@NotNull Task task) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_task WHERE project_id = '"+ task.getProjectId() +"'";
        @NotNull final PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }
    @SneakyThrows
    public List<Task> findAllTasks(@NotNull Task task) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_task WHERE user_id = '"+ task.getUserId() +"'";
        @NotNull final PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    @Nullable
    @SneakyThrows
    private Task fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Task task = new Task();
        task.setId(row.getString(FieldConst.ID));
        task.setName(row.getString(FieldConst.NAME));
        task.setDescription(row.getString(FieldConst.DESCRIPTION));
        task.setDateStart(row.getDate(FieldConst.DATE_START));
        task.setDateFinish(row.getDate(FieldConst.DATE_FINISH));
        task.setUserId(row.getString(FieldConst.USER_ID));
        return task;
    }

}
