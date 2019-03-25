package ru.zagorodnikova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.enumeration.FieldConst;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository<Project> {

    @NotNull private final Connection connection;

    public ProjectRepository(ServiceLocator serviceLocator) throws Exception {
        this.connection = serviceLocator.getConnection();
    }

    @Override
    public void remove(@NotNull String id) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_project " +
                "WHERE id = '"+ id +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    public void removeAll(@NotNull final String userId) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_project " +
                "WHERE user_id = '"+ userId +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @NotNull
    @SneakyThrows
    public List<Project> getProjects() {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_project";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    public void setProjects(@NotNull final List<Project> list) {
        for (Project v : list) {
            persist(v);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public Project persist(@NotNull Project project) {
        @NotNull final String query = "INSERT INTO todo_list.app_project " +
                "(id, user_id, name, description, dateStart, dateFinish, dateCreate) \n" +
                " VALUES ('"+ project.getId()+"', '"+ project.getUserId() +"', '"+ project.getName() +"', '" +
                project.getDescription() +"', '" +
                DateFormatterUtil.dateFormatter(project.getDateStart()) +"', '" +
                DateFormatterUtil.dateFormatter(project.getDateFinish()) +"', '" +
                DateFormatterUtil.dateFormatter(project.getDateCreate()) +"');";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
        return project;
    }

    @Nullable
    @SneakyThrows
    public Project findOne(@NotNull final String userId, @NotNull final String name){
        @NotNull final String query =
                "SELECT * FROM todo_list.app_project WHERE user_id = '" + userId + "' AND name = '" + name + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            System.out.println(resultSet);
            Project newProject = fetch(resultSet);
            statement.close();
            return newProject;
        }
        return null;
    }

    @SneakyThrows
    public void merge(@NotNull final String id,
                      @NotNull final String projectName,
                      @NotNull final String description,
                      @NotNull final Date dateStart,
                      @NotNull final Date dateFinish) {
        @NotNull final String query =  "UPDATE todo_list.app_project SET " +
                "description = '"+ description +"', " +
                "name = '"+ projectName +"', " +
                "dateStart = '"+ DateFormatterUtil.dateFormatter(dateStart) +"', " +
                "dateFinish = '"+ DateFormatterUtil.dateFormatter(dateFinish) +"'  " +
                "WHERE id = '"+ id +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @NotNull
    public List<Project> sortByDateCreated(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> (o2).getDateCreate().compareTo((o1).getDateCreate())));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Project> sortByDateStart(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateStart())
                .compareTo(Objects.requireNonNull((o1).getDateStart()))));
        Collections.reverse(list);
        return list;
    }
    @NotNull
    public List<Project> sortByDateFinish(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> Objects.requireNonNull((o2).getDateFinish())
                .compareTo(Objects.requireNonNull((o1).getDateFinish()))));
        Collections.reverse(list);
        return list;
    }

    @NotNull
    public List<Project> sortByStatus(@NotNull final List<Project> list) {
        list.sort(((o1, o2) -> (o2).getStatus().compareTo((o1).getStatus())));
        Collections.reverse(list);
        return list;
    }

    @SneakyThrows
    public List<Project> findAll(@NotNull String userId) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_project WHERE user_id = '"+ userId +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    @Nullable
    @SneakyThrows
    private Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Project project = new Project();
        project.setId(row.getString(FieldConst.ID));
        project.setUserId(row.getString(FieldConst.USER_ID));
        project.setName(row.getString(FieldConst.NAME));
        project.setDescription(row.getString(FieldConst.DESCRIPTION));
        project.setDateFinish(row.getDate(FieldConst.DATE_FINISH));
        project.setDateStart(row.getDate(FieldConst.DATE_START));
        return project;
    }

}
