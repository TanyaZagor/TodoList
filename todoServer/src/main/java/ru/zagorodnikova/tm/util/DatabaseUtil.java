package ru.zagorodnikova.tm.util;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.mapper.IProjectMapper;
import ru.zagorodnikova.tm.api.mapper.ISessionMapper;
import ru.zagorodnikova.tm.api.mapper.ITaskMapper;
import ru.zagorodnikova.tm.api.mapper.IUserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseUtil {
    public Connection getConnection() throws Exception {
        @NotNull final Properties property = getProperties();
        @NotNull final int portDB = Integer.valueOf( property.getProperty("portDB"));
        @NotNull final String hostDB = property.getProperty("hostDB");
        @NotNull final String userDB = property.getProperty("userDB");
        @NotNull final String passwordDB = property.getProperty("passwordDB");
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ hostDB +":" + portDB +"/todo_list", userDB, passwordDB);
        return connection;
    }

    private Properties getProperties() throws Exception {
        @NotNull final Properties property = new Properties();
        property.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        return property;
    }

    public static SqlSessionFactory getSqlSessionFactory() throws Exception {
        @Nullable final String user = "root";
        @Nullable final String password = "root";
        @Nullable final String url = "jdbc:mysql://localhost:3306/todo_list";
        @Nullable final String driver = "com.mysql.jdbc.Driver";

        final DataSource dataSource = new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        final Environment environment =
                new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(IUserMapper.class);
        configuration.addMapper(IProjectMapper.class);
        configuration.addMapper(ISessionMapper.class);
        configuration.addMapper(ITaskMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
