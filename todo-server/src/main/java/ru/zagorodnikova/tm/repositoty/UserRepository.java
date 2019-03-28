package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepository implements IUserRepository {

    @NotNull private final EntityManager entityManager;

    public UserRepository (@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findOne(@NotNull final String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void persist(@NotNull final User user) {
        entityManager.persist(user);
    }

    @Override
    public User signIn(@NotNull final String login,
                @NotNull final String password) {
        User user = (User) entityManager.createQuery("SELECT user FROM User user WHERE user.login = ?1 AND user.password = ?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .getSingleResult();
        return user;
    }

    @Override
    public void remove(@NotNull final User user) {
        entityManager.remove(user);
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("Delete from User user where user.roleType = USER").executeUpdate();
    }

    @Override
    public List<User> getUsers() {
        List list = entityManager.createQuery("Select user from User user")
                .getResultList();
        return list;
    }

    @Override
    public void merge(@NotNull final User user) {
        entityManager.merge(user);
    }
}
