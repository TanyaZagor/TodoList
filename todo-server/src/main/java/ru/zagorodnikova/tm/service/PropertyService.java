package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;

@ApplicationScoped
public class PropertyService {

    public Properties getProperties() throws Exception {
        @NotNull final Properties property = new Properties();
        property.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        return property;
    }

    public String getJdbcDriver() throws Exception {
        return getProperties().getProperty("driverDB");
    }

    public String getJdbcUrl() throws Exception {
        return getProperties().getProperty("urlDB");
    }

    public String getJdbcUsername() throws Exception {
        return getProperties().getProperty("userDB");
    }

    public String getJdbcPassword() throws Exception {
        return getProperties().getProperty("passwordDB");
    }

    public String getHost() throws Exception {
        return getProperties().getProperty("host");
    }

    public String getPort() throws Exception {
        return getProperties().getProperty("port");
    }
}
