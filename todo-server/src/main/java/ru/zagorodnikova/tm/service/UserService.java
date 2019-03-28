package ru.zagorodnikova.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repositoty.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class UserService extends AbstractService implements IUserService {

    @NotNull private final ServiceLocator serviceLocator;

    public UserService(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public User signIn(@NotNull final String login, @NotNull final String password) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        return userRepository.signIn(login, password);
    }

    @Nullable
    public User signUp(@NotNull final String login, @NotNull final String password, @NotNull final String fistName,
                       @NotNull final String lastName, @NotNull final String email) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        if (fistName.isEmpty()) return null;
        if (lastName.isEmpty()) return null;
        if (email.isEmpty()) return null;
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        @NotNull final User user = new User(login, password, fistName, lastName, email);
        entityManager.getTransaction().begin();
        userRepository.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public void changePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String oldPassword,
                               @NotNull final String newPassword) throws Exception {
        if (login.isEmpty()) return;
        if (oldPassword.isEmpty()) return;
        if (newPassword.isEmpty()) return;
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        User user = userRepository.findOne(userId);
        if (user != null) {
            entityManager.getTransaction().begin();
            user.setPassword(newPassword);
            entityManager.getTransaction().commit();
        }
    }

    public void updateUser(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName,
                           @NotNull String email){
        if (firstName.isEmpty()) return;
        if (lastName.isEmpty()) return;
        if (email.isEmpty()) return;
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        User user = userRepository.findOne(userId);
        if (user != null) {
            entityManager.getTransaction().begin();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            entityManager.getTransaction().commit();
        }
    }

    public void removeUser(@NotNull final String userId) {
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            userRepository.remove(userId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        return userRepository.findOne(userId);
    }

    @Override
    public void removeAll() {
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        userRepository.removeAll();
        entityManager.getTransaction().commit();
    }

    @Nullable
    public List<User> getUsers() {
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        return userRepository.getUsers();
    }

    public void setUsers(@NotNull final List<User> list) {
        EntityManager entityManager = serviceLocator.getFactory().createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        for (User user : list) {
            userRepository.persist(user);
        }
        entityManager.getTransaction().commit();
    }

}
