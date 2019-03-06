package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.HashPassword;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository implements IUserRepository {

    private Map<String, User> users = new LinkedHashMap<>();

    public User signIn(String login, String password) {
        Map<String, User> map = new LinkedHashMap<>();
        users.forEach((k, v) -> {
            if (v.getLogin().equals(login) && v.getPassword().equals(HashPassword.hashPassword(password))) {
                map.put(v.getLogin(), v);
            }
        });
        if (map.size() != 0) {
            User user = map.get(login);
            return user;
        }
        return null;
    }

    public User signUp(String login, String password, String firstName, String lastName, String email, RoleType roleType) {
        User user = new User(login, password, firstName, lastName, email, roleType);
        if (!users.containsValue(user)) {
            users.put(user.getId(), user);
            return user;
        }
        return null;
    }

    public void update(String userId, String firstName, String lastName, String email) {
        users.get(userId).setFirstName(firstName);
        users.get(userId).setLastName(lastName);
        users.get(userId).setEmail(email);
    }

    public void changePassword(String userId, String login, String password, String newPassword) {
        if (users.get(userId).getPassword().equals(password) && users.get(userId).getLogin().equals(login)) {
            users.get(userId).setPassword(newPassword);
        }
    }

}
