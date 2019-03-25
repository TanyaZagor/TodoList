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

    @NotNull private final Connection connection;

    public TaskRepository(ServiceLocator serviceLocator) throws Exception {
        this.connection = serviceLocator.getConnection();
    }

    @Nullable
    @Override
    public Task persist(@NotNull final Task task) throws Exception {
        @NotNull final String query = "INSERT INTO todo_list.app_task " +
                "(id, user_id, project_id, name, description, dateStart, dateFinish, dateCreate) \n" +
                " VALUES ('"+ task.getId()+"', '"+ task.getUserId() +"', '"+ task.getProjectId() +"', '"
                + task.getName() +"', '"+ task.getDescription() +"', '"
                + DateFormatterUtil.dateFormatter(task.getDateStart()) +"', '"
                + DateFormatterUtil.dateFormatter(task.getDateFinish()) +"', '"
                + DateFormatterUtil.dateFormatter(task.getDateCreate()) +"');";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
        return task;
    }

    @Override
    public void remove(@NotNull final String id) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_task " +
                "WHERE id = '"+ id +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    public void merge(@NotNull final String id,
                      @NotNull final String name,
                      @NotNull final String description,
                      @NotNull final Date dateStart,
                      @NotNull final Date dateFinish) throws Exception {
        @NotNull final String query =  "UPDATE todo_list.app_task SET " +
                "name = '"+ name +"', " +
                "description = '"+ description +"', " +
                "dateStart = '"+ DateFormatterUtil.dateFormatter(dateStart) +"', " +
                "dateFinish = '"+ DateFormatterUtil.dateFormatter(dateFinish) +"'  " +
                "WHERE id = '"+ id +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }


    public void removeAll(@NotNull final String userId) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_task " +
                "WHERE user_id = '"+ userId +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    public void removeAllInProject(@NotNull final String projectId) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_task " +
                "WHERE project_id = '"+ projectId +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Nullable
    @SneakyThrows
    public Task findOne(@NotNull final String projectId, @NotNull final String name) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_task WHERE name = '" + name + "' AND project_id = '" + projectId + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Task newTask = fetch(resultSet);
            statement.close();
            return newTask;
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
    @SneakyThrows
    public List<Task> getTasks() {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_task ";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    public void setTasks(@NotNull final List<Task> list) throws Exception {
        for (Task task : list) {
            persist(task);
        }
    }

    @SneakyThrows
    public List<Task> findAllTasksInProject(@NotNull final String projectId) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_task WHERE project_id = '"+ projectId +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    @SneakyThrows
    public List<Task> findAllTasks(@NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_task WHERE user_id = '"+ userId +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
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
        task.setProjectId(row.getString(FieldConst.PROJECT_ID));
        return task;
    }
}
