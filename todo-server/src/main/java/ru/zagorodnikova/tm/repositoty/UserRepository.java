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
    public User findOne(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void persist(@NotNull final User user) {
        entityManager.persist(user);
    }

    @Override
    public User signIn(@NotNull final String login,
                @NotNull final String password) {
        User user = (User) entityManager.createQuery("Select * from app_user where login = ?1 and passwordHash = ?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .getSingleResult();
        return user;
    }

    @Override
    public void remove(@NotNull final String id) {
        entityManager.remove(id);
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("Delete from app_user where role = USER").executeUpdate();
    }

    @Override
    public List<User> getUsers() {
        List list = entityManager.createQuery("Select * from app_user")
                .getResultList();
        return list;
    }

    @Override
    public void merge(@NotNull final User user) {
        entityManager.merge(user);
    }
}
