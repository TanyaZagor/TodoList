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

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository<Project> {

    @NotNull private final Map<String, Project> projects = super.getMap();
    @NotNull private final Connection connection;

    public ProjectRepository(ServiceLocator serviceLocator) throws Exception {
        this.connection = serviceLocator.getConnection();
    }

    @Override
    public void remove(@NotNull Project project) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_project " +
                "WHERE id = '"+ project.getId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        projects.remove(project.getId());
    }
    public void removeAll(@NotNull final Project project) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_project " +
                "WHERE user_id = '"+ project.getUserId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        projects.entrySet().removeIf((v) -> Objects.equals(v.getValue().getUserId(), project.getUserId()));
    }

    @NotNull
    @SneakyThrows
    public List<Project> getProjects() {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_project";
        @NotNull final PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    public void setProjects(@NotNull final List<Project> list) {
        projects.clear();
        list.forEach((v) -> projects.put(v.getId(), v));
    }

    @Nullable
    @Override
    @SneakyThrows
    public Project persist(@NotNull Project project) {
        @NotNull final String query = "INSERT INTO todo_list.app_project (id, user_id, name, description, dateStart, dateFinish, dateCreate) \n" +
                " VALUES ('"+ project.getId()+"', '"+ project.getUserId() +"', '"+ project.getName() +"', '"+ project.getDescription() +"', '"+ DateFormatterUtil.dateFormatter(project.getDateStart()) +"', '"+ DateFormatterUtil.dateFormatter(project.getDateFinish()) +"', '"+ DateFormatterUtil.dateFormatter(project.getDateCreate()) +"');";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        if (!projects.containsValue(project)) {
            projects.put(project.getId(), project);
            return project;
        }
        return null;
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

    @SneakyThrows
    public void merge(@NotNull final Project project) {
        @NotNull final String query =  "UPDATE todo_list.app_project SET " +
                "description = '"+ project.getDescription() +"', " +
                "name = '"+ project.getName() +"', " +
                "dateStart = '"+ DateFormatterUtil.dateFormatter(project.getDateStart()) +"', " +
                "dateFinish = '"+ DateFormatterUtil.dateFormatter(project.getDateFinish()) +"'  " +
                "WHERE id = '"+ project.getId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
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

    @SneakyThrows
    public List<Project> findAll(@NotNull Project project) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_project WHERE user_id = '"+ project.getUserId() +"'";
        @NotNull final PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
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
        project.setName(row.getString(FieldConst.NAME));
        project.setDescription(row.getString(FieldConst.DESCRIPTION));
        project.setDateStart(row.getDate(FieldConst.DATE_START));
        project.setDateFinish(row.getDate(FieldConst.DATE_FINISH));
        project.setUserId(row.getString(FieldConst.USER_ID));
        return project;
    }

}
