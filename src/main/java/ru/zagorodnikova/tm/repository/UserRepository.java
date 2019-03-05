package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.utils.Utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository implements IUserRepository {

    private final Bootstrap bootstrap = Bootstrap.getBootstrap();
    private Map<String, User> users = bootstrap.getUsers();

    public void signIn(String login, String password) {
        Map<String, User> map = new LinkedHashMap<>();
        users.forEach((k, v) -> {
            if (v.getLogin().equals(login) && v.getPassword().equals(Utils.hashPassword(password))) {
                map.put(v.getLogin(), v);
            }
        });
        if (map.size() != 0) {
            User user = map.get(login);
            bootstrap.setCurrentUser(user);
        }

    }

    public void signOut() {
        bootstrap.setCurrentUser(null);
    }

    public void signUp(String login, String password, String firstName, String lastName, String email) {
        User user = new User(login, password, firstName, lastName, email);
        if (!users.containsValue(user)) {
            users.put(user.getId(), user);
            bootstrap.setCurrentUser(user);
        }
    }

    public void update(String firstName, String lastName, String email) {
        bootstrap.getCurrentUser().setFirstName(firstName);
        bootstrap.getCurrentUser().setLastName(lastName);
        bootstrap.getCurrentUser().setEmail(email);
    }

    public void changePassword(String login, String password, String newPassword) {
        users.forEach((k, v) -> {
            if (v.getLogin().equals(login) && v.getPassword().equals(Utils.hashPassword(password))) {
                v.setPassword(Utils.hashPassword(password));
            }
        });
    }
}
