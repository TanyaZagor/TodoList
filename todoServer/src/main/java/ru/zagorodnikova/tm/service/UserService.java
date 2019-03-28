package ru.zagorodnikova.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.UserRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

import java.util.List;

public class UserService extends AbstractService implements IUserService {

    @NotNull private final SqlSessionFactory sqlSessionFactory;

    public UserService(@NotNull final ServiceLocator serviceLocator) {
        this.sqlSessionFactory = serviceLocator.getSessionFactory();
    }

    @Nullable
    public User signIn(@NotNull final String login, @NotNull final String password) throws Exception {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            return userRepository.signIn(login, PasswordUtil.hashPassword(password));
        }
    }

    @Nullable
    public User signUp(@NotNull final String login, @NotNull final String password, @NotNull final String fistName,
                       @NotNull final String lastName, @NotNull final String email) {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        if (fistName.isEmpty()) return null;
        if (lastName.isEmpty()) return null;
        if (email.isEmpty()) return null;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
                @NotNull final User user = new User(login, password, fistName, lastName, email);
                userRepository.persist(user);
                sqlSession.commit();
                return user;
            } catch (Exception e) {
                sqlSession.rollback();
                return null;
            }
        }
    }

    public void changePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String oldPassword,
                               @NotNull final String newPassword) {
        if (login.isEmpty()) return;
        if (oldPassword.isEmpty()) return;
        if (newPassword.isEmpty()) return;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
                if (userRepository.checkPassword(login, PasswordUtil.hashPassword(oldPassword)) != null) {
                    userRepository.changePassword(userId, PasswordUtil.hashPassword(newPassword));
                    sqlSession.commit();
                }
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }

    public void updateUser(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName,
                           @NotNull String email){
        if (firstName.isEmpty()) return;
        if (lastName.isEmpty()) return;
        if (email.isEmpty()) return;
        try(SqlSession sqlSession= sqlSessionFactory.openSession()) {
            try {
                UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
                userRepository.merge(userId, firstName, lastName, email);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }

    public void removeUser(@NotNull final String userId) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
                userRepository.remove(userId);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }

    @Nullable
    public List<User> findAllUsers(@NotNull final String userId) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            return userRepository.getUsers();
        }

    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            return userRepository.findOne(userId);
        }
    }

    @Override
    public void removeAll() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
                userRepository.removeAll();
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }

    @Nullable
    public List<User> getUsers() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            return userRepository.getUsers();
        }
    }

    public void setUsers(@NotNull final List<User> list) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
                for (User user : list) {
                    userRepository.persist(user);
                }
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
    }
}
