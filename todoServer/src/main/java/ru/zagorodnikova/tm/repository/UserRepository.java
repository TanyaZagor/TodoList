package ru.zagorodnikova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.enumeration.FieldConst;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.print.DocFlavor;
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
    public User signIn(@NotNull final String login, @NotNull final String password) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user WHERE login = '" + login + "' AND passwordHash = '" + password + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            User newUser = fetch(resultSet);
            statement.close();
            return newUser;
        }
        return null;
    }

    public void changePassword(@NotNull final String userId, @NotNull final String password) throws Exception {
        @NotNull final String query =  "UPDATE todo_list.app_user SET " +
                "passwordHash = '"+ password +
                "' WHERE id = '" + userId + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void remove(@NotNull final String userId) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_user " +
                "WHERE id = '"+ userId +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query =  "DELETE FROM todo_list.app_user " +
                "WHERE role = '"+ RoleType.USER +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Nullable
    @Override
    public User persist(@NotNull User user) throws Exception {
        @NotNull final String query = "INSERT INTO todo_list.app_user (id, login, passwordHash, firstName, lastName, email, role) \n" +
                " VALUES ('"+ user.getId()+"', '"+ user.getLogin() +"', '"+ user.getPassword() +"', '"+ user.getFirstName() +"', '"+ user.getLastName() +"', '"+ user.getEmail() +"', '"+ user.getRoleType() +"');";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
        return user;
    }

    @Nullable
    @SneakyThrows
    public User findOne(@NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user WHERE id = '" + userId + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if ( resultSet.next()) {
            User newUser = fetch(resultSet);
            statement.close();
            return newUser;
        }
        return null;
    }

    public void merge(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName, @NotNull final String email) throws Exception {
        @NotNull final String query =  "UPDATE todo_list.app_user SET " +
                "firstName = '"+ firstName +"', " +
                "lastName = '"+ lastName +"', " +
                "email = '"+ email +"' WHERE id = '" + userId + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @SneakyThrows
    public boolean checkPassword(@NotNull final String login, @NotNull final String password) {
        @NotNull final String query =
                "SELECT * FROM todo_list.app_user WHERE login = '" + login + "' AND passwordHash = '" + password + "'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (fetch(resultSet) != null) {
                statement.close();
                return true;
            }
        }
        statement.close();
        return false;
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
