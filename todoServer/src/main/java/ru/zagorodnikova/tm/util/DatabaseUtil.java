package ru.zagorodnikova.tm.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;


public class DatabaseUtil {

    public static SqlSessionFactory getSqlSessionFactory() throws Exception {
        @NotNull final String resource = "mybatis-config.xml";
        @NotNull final InputStream inputStream  = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

}
