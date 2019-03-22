package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;


public class UserRepository extends AbstractRepository<User> implements IUserRepository<User> {

    @NotNull private final Map<String, User> users = super.getMap();
    @NotNull private final Connection connection;

    public UserRepository(ServiceLocator serviceLocator) throws Exception {
        this.connection = serviceLocator.getConnection();
    }

    @Nullable
    public User signIn(@NotNull final User user) {
        @NotNull final Map<String, User> map = new LinkedHashMap<>();
        users.forEach((k, v) -> {
            if (Objects.equals(v.getLogin(), user.getLogin()) && Objects.equals(v.getPassword(), user.getPassword())) {
                map.put(v.getLogin(), v);
            }
        });
        if (map.size() != 0) {
            return map.get(user.getLogin());
        }
        return null;
    }

    public void changePassword(@NotNull final User user) throws Exception {
        if (user.getPassword() != null) {
            users.get(user.getId()).setPassword(user.getPassword());
        }
    }

    @Override
    public void remove(@NotNull User user) throws Exception {
        @NotNull final String query =  "DELETE FROM todo_list.app_user " +
                "WHERE id = '"+ user.getId() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        users.remove(user.getId());
    }

    @Override
    public void removeAll() {
        users.entrySet().removeIf((v) -> !(v.getValue().getRoleType().equals(RoleType.ADMIN)));
    }

    @Nullable
    @Override
    public User persist(@NotNull User user) throws Exception {
        @NotNull final String query = "INSERT INTO todo_list.app_user (id, login, passwordHash, firstName, lastName, email, role) \n" +
                " VALUES ('"+ user.getId()+"', '"+ user.getLogin() +"', '"+ user.getFirstName() +"', '"+ user.getLastName() +"', '"+ user.getEmail() +"', '"+ user.getEmail() +"', '"+ user.getRoleType() +"');";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        if (!users.containsValue(user)) {
            users.put(user.getId(), user);
            return user;
        }
        return null;
    }

    @NotNull
    @Override
    public User findOne(@NotNull final User user) {
        return users.get(user.getId());
    }

    @Override
    public void merge(@NotNull final User user) throws Exception {
        @NotNull final String query =  "UPDATE todo_list.app_task SET " +
                "firstName = '"+ user.getFirstName() +"', " +
                "lastName = '"+ user.getLastName() +"', " +
                "email = '"+ user.getEmail() +"'";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        users.get(user.getId()).setFirstName(user.getFirstName());
        users.get(user.getId()).setLastName(user.getLastName());
        users.get(user.getId()).setEmail(user.getEmail());
    }

    @Nullable
    @Override
    public List<User> findAll(@NotNull final User user) {
        @NotNull final List<User> usersList = new ArrayList<>();
        users.forEach((k, v) -> usersList.add(v));
        return usersList;
    }

    public boolean checkPassword(@NotNull final User user) {
        return Objects.equals(users.get(user.getId()).getPassword(), user.getPassword()) && Objects.equals(users.get(user.getId()).getLogin(), user.getLogin());
    }

    @NotNull
    public List<User> getUsers() {
        @NotNull final List<User> list = new ArrayList<>();
        users.forEach((k, v) -> list.add(v));
        return list;
    }

    public void setUsers(@NotNull final List<User> list) {
        users.clear();
        list.forEach((v) -> users.put(v.getId(), v));
    }

}
