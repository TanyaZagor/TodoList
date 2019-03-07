package ru.zagorodnikova.tm.repository;

import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.UtilPassword;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends AbstractRepository implements IUserRepository {

    private final Map<String, User> users = new LinkedHashMap<>();

    public User signIn(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        Map<String, User> map = new LinkedHashMap<>();
        users.forEach((k, v) -> {
            if (v.getLogin().equals(user.getLogin()) && v.getPassword().equals(user.getPassword())) {
                map.put(v.getLogin(), v);
            }
        });
        if (map.size() != 0) {
            return map.get(user.getLogin());
        }
        return null;
    }

    public void changePassword(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        users.get(user.getId()).setPassword(user.getPassword());
    }

    @Override
    public AbstractEntity persist(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        if (!users.containsValue(user)) {
            users.put(user.getId(), user);
            return user;
        }
        return null;
    }

    @Override
    public void remove(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        users.remove(user.getId());
    }

    @Override
    public void removeAll(AbstractEntity abstractEntity) {
        users.entrySet().removeIf((v) -> !v.getValue().getRoleType().equals(RoleType.ADMIN));
    }

    @Override
    public AbstractEntity findOne(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        final List<User> list = new ArrayList<>();
        users.forEach((k, v) -> {
            if (v.getLogin().equals(user.getLogin())) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void merge(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        users.get(user.getId()).setFirstName(user.getFirstName());
        users.get(user.getId()).setLastName(user.getLastName());
        users.get(user.getId()).setEmail(user.getEmail());
    }

    @Override
    public List<AbstractEntity> findAll(AbstractEntity abstractEntity) {
        return null;
    }

    public boolean checkPassword(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        return users.get(user.getId()).getPassword().equals(user.getPassword()) && users.get(user.getId()).getLogin().equals(user.getLogin());
    }
}
