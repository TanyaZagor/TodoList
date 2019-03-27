package ru.zagorodnikova.tm.repository;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.mapper.IUserMapper;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.enumeration.FieldConst;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class UserRepository extends AbstractRepository<User> implements IUserRepository<User> {

    @NotNull private final SqlSession sqlSession;
    @NotNull private final IUserMapper userMapper;

    public UserRepository(ServiceLocator serviceLocator) {
        sqlSession = serviceLocator.getSessionFactory().openSession();
        this.userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Nullable
    @SneakyThrows
    public User signIn(@NotNull final String login, @NotNull final String password) {
        return userMapper.signIn(login, password);
    }

    public void changePassword(@NotNull final String userId, @NotNull final String password) {
        userMapper.checkPassword(userId, password);
        sqlSession.commit();
    }

    @Override
    public void remove(@NotNull final String userId) {
        userMapper.remove(userId);
        sqlSession.commit();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        userMapper.removeAll();
        sqlSession.commit();
    }

    @Nullable
    @Override
    public User persist(@NotNull User user) {
        userMapper.persist(user);
        sqlSession.commit();
        return user;
    }

    @Nullable
    @SneakyThrows
    public User findOne(@NotNull final String userId) {
        return userMapper.findOne(userId);
    }

    public void merge(@NotNull final String userId, @NotNull final String firstName, @NotNull final String lastName, @NotNull final String email) {
        userMapper.merge(userId, firstName, lastName, email);
        sqlSession.commit();
    }

    @SneakyThrows
    public boolean checkPassword(@NotNull final String login, @NotNull final String password) {
        String result = userMapper.checkPassword(login, password);
        return result != null;
    }

    @Nullable
    @SneakyThrows
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    public void setUsers(@NotNull final List<User> list) {
        for (User user : list) {
            persist(user);
        }
    }
}
