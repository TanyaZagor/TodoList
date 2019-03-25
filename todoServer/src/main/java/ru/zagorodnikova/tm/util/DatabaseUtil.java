package ru.zagorodnikova.tm.util;

import org.jetbrains.annotations.NotNull;

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
}
