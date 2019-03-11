package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public class UserService extends AbstractService implements IUserService {

    private final IUserRepository<AbstractEntity> userRepository;

    public UserService(IUserRepository<AbstractEntity> userRepository) {
        this.userRepository = userRepository;
    }

    public AbstractEntity signIn(String login, String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userRepository.signIn(user);
    }

    public AbstractEntity signUp(String login, String password, String fistName, String lastName, String email, RoleType roleType) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        if (fistName == null || fistName.isEmpty()) return null;
        if (lastName == null || lastName.isEmpty()) return null;
        if (email == null || email.isEmpty()) return null;
        User user = new User(login, password, fistName, lastName, email, roleType);
        return userRepository.persist(user);
    }

    public void changePassword(String userId, String login, String oldPassword, String newPassword) {
        if (login == null || login.isEmpty()) return;
        if (oldPassword == null || oldPassword.isEmpty()) return;
        if (newPassword == null || newPassword.isEmpty()) return;
        User user = new User();
        user.setId(userId);
        user.setPassword(oldPassword);
        user.setLogin(login);
        if (userRepository.checkPassword(user)) {
            user.setPassword(newPassword);
            userRepository.changePassword(user);
        }

    }

    public void update(String userId, String firstName, String lastName, String email) {
        if (firstName == null || firstName.isEmpty()) return;
        if (lastName == null || lastName.isEmpty()) return;
        if (email == null || email.isEmpty()) return;
        User user = new User();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.merge(user);
    }

    public void remove(String userId) {
        User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

    public void removeAll(String userId) {
        User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

    public List<AbstractEntity> findAll(RoleType roleType) {
        User user = new User();
        user.setRoleType(roleType);
        return userRepository.findAll(user);
    }
}
