package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repositoty.UserRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public class UserService implements IUserService {

    @Inject
    private UserRepository userRepository;


    @Nullable
    public User signIn(@NotNull final String login, @NotNull final String password) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        try {
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
        try {
            @NotNull final User user = new User(login, password, fistName, lastName, email);
            userRepository.getEntityManager().getTransaction().begin();
            userRepository.persist(user);
            userRepository.getEntityManager().getTransaction().commit();
            return user;
        } catch (Exception e) {
            userRepository.getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    public void changePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String oldPassword,
                               @NotNull final String newPassword) throws Exception {
        if (login.isEmpty()) return;
        if (oldPassword.isEmpty()) return;
        if (newPassword.isEmpty()) return;
        try {
            User user = userRepository.findOne(userId);
            if (user != null) {
                userRepository.getEntityManager().getTransaction().begin();
                user.setPassword(newPassword);
                userRepository.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            userRepository.getEntityManager().getTransaction().rollback();
        }
    }

    public void updateUser(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName,
                           @NotNull String email){
        if (firstName.isEmpty()) return;
        if (lastName.isEmpty()) return;
        if (email.isEmpty()) return;
        try {
            User user = userRepository.findOne(userId);
            if (user != null) {
                userRepository.getEntityManager().getTransaction().begin();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                userRepository.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            userRepository.getEntityManager().getTransaction().rollback();
        }
    }

    public void removeUser(@NotNull final String userId) {
        try {
            User user = userRepository.findOne(userId);
            if (user == null) return;
            try {
                userRepository.getEntityManager().getTransaction().begin();
                userRepository.remove(user);
                userRepository.getEntityManager().getTransaction().commit();
            } catch (Exception e) {
                userRepository.getEntityManager().getTransaction().rollback();
            }
        } catch (Exception e) {
            userRepository.getEntityManager().getTransaction().rollback();
        }
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        try {
            return userRepository.findOne(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeAll() {
        try {
            userRepository.getEntityManager().getTransaction().begin();
            userRepository.removeAll();
            userRepository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            userRepository.getEntityManager().getTransaction().rollback();
        }
    }

    @Nullable
    public List<User> getUsers() {
        try {
            return userRepository.getUsers();
        } catch (Exception e) {
            return null;
        }
    }

    public void setUsers(@NotNull final List<User> list) {
        try {
            userRepository.getEntityManager().getTransaction().begin();
            for (User user : list) {
                userRepository.persist(user);
            }
            userRepository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            userRepository.getEntityManager().getTransaction().rollback();
        }
    }

}
