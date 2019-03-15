package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.util.*;

public class UserRepository extends AbstractRepository<User> implements IUserRepository<User> {

    @NotNull private final Map<String, User> users = new LinkedHashMap<>();

    @Nullable
    public User signIn(@NotNull User user) {
        final Map<String, User> map = new LinkedHashMap<>();
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

    public void changePassword(@NotNull User user) {
        if (user.getPassword() != null) {
            users.get(user.getId()).setPassword(user.getPassword());
        }
    }

    @Nullable
    @Override
    public User persist(@NotNull User user) {
        if (!users.containsValue(user)) {
            users.put(user.getId(), user);
            return user;
        }
        return null;
    }

    @Override
    public void remove(@NotNull User user) {
        users.remove(user.getId());
    }

    @Override
    public void removeAll(@NotNull User abstractEntity) {
        users.entrySet().removeIf((v) -> !v.getValue().getRoleType().equals(RoleType.ADMIN));
    }

    @Nullable
    @Override
    public User findOne(@NotNull User user) {
        final List<User> list = new ArrayList<>();
        users.forEach((k, v) -> {
            if (Objects.equals(v.getLogin(), user.getLogin())) {
                list.add(v);
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void merge(@NotNull User user) {
        users.get(user.getId()).setFirstName(user.getFirstName());
        users.get(user.getId()).setLastName(user.getLastName());
        users.get(user.getId()).setEmail(user.getEmail());
    }

    @Nullable
    @Override
    public List<User> findAll(@NotNull User user) {
        @NotNull final List<User> usersList = new ArrayList<>();
        users.forEach((k, v) -> usersList.add(v));
        return usersList;
    }

    public boolean checkPassword(@NotNull User user) {
        return Objects.equals(users.get(user.getId()).getPassword(), user.getPassword()) && Objects.equals(users.get(user.getId()).getLogin(), user.getLogin());
    }

}
