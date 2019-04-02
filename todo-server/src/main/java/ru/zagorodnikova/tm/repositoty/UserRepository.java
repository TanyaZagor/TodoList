package ru.zagorodnikova.tm.repositoty;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.User;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepository implements IUserRepository {

    @Inject
    private EntityManager entityManager;

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
        User user = entityManager.createQuery("SELECT user FROM User user WHERE user.login =: login AND user.password =: password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        return user;
    }

    @Override
    public void remove(@NotNull final User user) {
        entityManager.remove(user);
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("DELETE FROM User user WHERE user.roleType = USER").executeUpdate();
    }

    @Override
    public List<User> getUsers() {
        List<User> list = entityManager.createQuery("SELECT user FROM User user", User.class)
                .getResultList();
        return list;
    }

    @Override
    public void merge(@NotNull final User user) {
        entityManager.merge(user);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
