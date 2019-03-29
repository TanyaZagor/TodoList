package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repositoty.UserRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@NoArgsConstructor
public class UserService extends AbstractService implements IUserService {

    @Inject
    private EntityManagerFactory factory;

    @Nullable
    public User signIn(@NotNull final String login, @NotNull final String password) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            return userRepository.signIn(login, PasswordUtil.hashPassword(password));
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    public User signUp(@NotNull final String login, @NotNull final String password, @NotNull final String fistName,
                       @NotNull final String lastName, @NotNull final String email) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        if (fistName.isEmpty()) return null;
        if (lastName.isEmpty()) return null;
        if (email.isEmpty()) return null;
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            @NotNull final User user = new User(login, password, fistName, lastName, email);
            entityManager.getTransaction().begin();
            userRepository.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public void changePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String oldPassword,
                               @NotNull final String newPassword) throws Exception {
        if (login.isEmpty()) return;
        if (oldPassword.isEmpty()) return;
        if (newPassword.isEmpty()) return;
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            User user = userRepository.findOne(userId);
            if (user != null) {
                entityManager.getTransaction().begin();
                user.setPassword(newPassword);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateUser(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName,
                           @NotNull String email){
        if (firstName.isEmpty()) return;
        if (lastName.isEmpty()) return;
        if (email.isEmpty()) return;
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            User user = userRepository.findOne(userId);
            if (user != null) {
                entityManager.getTransaction().begin();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeUser(@NotNull final String userId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            User user = userRepository.findOne(userId);
            if (user == null) return;
            try {
                entityManager.getTransaction().begin();
                userRepository.remove(user);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            return userRepository.findOne(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeAll() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            entityManager.getTransaction().begin();
            userRepository.removeAll();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    public List<User> getUsers() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            return userRepository.getUsers();
        } catch (Exception e) {
            return null;
        }
    }

    public void setUsers(@NotNull final List<User> list) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            UserRepository userRepository = new UserRepository(entityManager);
            entityManager.getTransaction().begin();
            for (User user : list) {
                userRepository.persist(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

}
