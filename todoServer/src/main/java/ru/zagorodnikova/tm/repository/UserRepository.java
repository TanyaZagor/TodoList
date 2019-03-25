package ru.zagorodnikova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.enumeration.FieldConst;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class UserRepository extends AbstractRepository<User> implements IUserRepository<User> {

    @NotNull private final Connection connection;

    public UserRepository(ServiceLocator serviceLocator) throws Exception {
        this.connection = serviceLocator.getConnection();
    }

    @Nullable
    @SneakyThrows
    public User signIn(@NotNull final User user) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user WHERE login = '" + user.getLogin() + "' AND passwordHash = '" + user.getPassword() + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return fetch(resultSet);
        }
        return null;
    }

    public void changePassword(@NotNull final User user) throws Exception {
        @NotNull final String query =  "UPDATE todo_list.app_user SET " +
                "passwordHash = '"+ user.getPassword() +
                "' WHERE id = '" + user.getId() + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

    @Override
    public void remove(@NotNull User user) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_user " +
                "WHERE id = '"+ user.getId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query =  "DELETE FROM todo_list.app_user " +
                "WHERE role = '"+ RoleType.USER +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

    @Nullable
    @Override
    public User persist(@NotNull User user) throws Exception {
        @NotNull final String query = "INSERT INTO todo_list.app_user (id, login, passwordHash, firstName, lastName, email, role) \n" +
                " VALUES ('"+ user.getId()+"', '"+ user.getLogin() +"', '"+ user.getPassword() +"', '"+ user.getFirstName() +"', '"+ user.getLastName() +"', '"+ user.getEmail() +"', '"+ user.getRoleType() +"');";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        return user;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findOne(@NotNull final User user) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user WHERE id = '" + user.getId() + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if ( resultSet.next()) {
            return fetch(resultSet);
        }
        return null;
    }

    @Override
    public void merge(@NotNull final User user) throws Exception {
        @NotNull final String query =  "UPDATE todo_list.app_user SET " +
                "firstName = '"+ user.getFirstName() +"', " +
                "lastName = '"+ user.getLastName() +"', " +
                "email = '"+ user.getEmail() +"' WHERE id = '" + user.getId() + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

    @Nullable
    @Override
    @SneakyThrows
    public List<User> findAll(@Nullable final User user) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<User> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    @SneakyThrows
    public boolean checkPassword(@NotNull final User user) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user WHERE login = '" + user.getLogin() + "' AND passwordHash = '" + user.getPassword() + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if ( !resultSet.next()) return false;
        if (fetch(resultSet) == null) return false;
        return true;
    }

    @Nullable
    @SneakyThrows
    public List<User> getUsers() {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user ";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<User> list = new ArrayList<>();
        while (resultSet.next()) list.add(fetch(resultSet));
        statement.close();
        return list;
    }

    public void setUsers(@NotNull final List<User> list) throws Exception {
        for (User user : list) {
            persist(user);
        }
    }

    @Nullable
    @SneakyThrows
    private User fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final User user = new User();
        user.setId(row.getString(FieldConst.ID));
        user.setLogin(row.getString(FieldConst.LOGIN));
        user.setPassword(row.getString(FieldConst.PASSWORD));
        user.setFirstName(row.getString(FieldConst.FIRST_NAME));
        user.setLastName(row.getString(FieldConst.LAST_NAME));
        user.setEmail(row.getString(FieldConst.EMAIL));
        user.setRoleType(row.getString(FieldConst.ROLETYPE));
        return user;
    }

}
