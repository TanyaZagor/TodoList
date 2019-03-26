package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.mapper.IProjectMapper;
import ru.zagorodnikova.tm.api.mapper.ITaskMapper;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.*;
import ru.zagorodnikova.tm.endpoint.*;
import ru.zagorodnikova.tm.entity.*;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.SessionRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.repository.UserRepository;
import ru.zagorodnikova.tm.service.*;
import ru.zagorodnikova.tm.util.DatabaseUtil;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Setter
@Getter
public class Bootstrap implements ServiceLocator {

    @NotNull private final Connection connection = new DatabaseUtil().getConnection();
    @NotNull private final IProjectRepository<Project> projectRepository = new ProjectRepository(this);
    @NotNull private final ITaskRepository<Task> taskRepository = new TaskRepository(this);
    @NotNull private final IUserRepository<User> userRepository = new UserRepository(this);
    @NotNull private final SessionRepository sessionRepository = new SessionRepository(this);
    @NotNull private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);
    @NotNull private final ITaskService taskService = new TaskService(taskRepository, projectRepository);
    @NotNull private final IUserService userService = new UserService(userRepository);
    @NotNull private final ISessionService sessionService = new SessionService(sessionRepository);
    @NotNull private final IDomainService domainService = new DomainService(projectRepository, taskRepository, userRepository);
    @NotNull private final IAdminService adminService = new AdminService(userRepository);

    public Bootstrap() throws Exception {
    }


    public void init() throws Exception {
        //initProjectsAndUsers();
        initEndpoints();
//        SqlSessionFactory sessionFactory = getSqlSessionFactory();
//        SqlSession session = sessionFactory.openSession();
//        Project project = session.getMapper(IProjectMapper.class).findOne("714bc724-ed0c-4215-9eb5-5bbd23a8473d", "Project2");
//        System.out.println(project.getName());
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        session.getMapper(IProjectMapper.class).merge("5f36d8ca-6d37-4fce-9195-a0f19fa52e03", "name", "des",  DateFormatterUtil.dateFormatter(new Date()), DateFormatterUtil.dateFormatter(new Date()));

        session.commit();
    }


    private void initProjectsAndUsers() throws Exception {
        final User user1 = userService.signUp("login", "password", "first name", "last name", "email@email.ru");
        final User user2 = userService.signUp("admin", "admin", "first name", "last name", "email@email.ru");
        user2.setRoleType("admin");

        final Project project1 = projectService.persistProject(user1.getId(), "Project1", "Description1", "20.02.2019", "20.05.2019");
        final Project project2 = projectService.persistProject(user2.getId(), "Project2", "Description2", "20.05.2019", "20.06.2019");
        final Project project3 = projectService.persistProject(user2.getId(), "Project1", "Description1", "20.02.2016", "20.05.2020");
        project3.setStatus(Status.DONE);

        taskService.persistTask(project1.getUserId(), project1.getName(), "task1", "des1", "20.02.2012", "20.02.2013");
        taskService.persistTask(project1.getUserId(), project1.getName(), "task2", "des2", "20.02.2010", "20.02.2011");
        taskService.persistTask(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persistTask(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persistTask(project3.getUserId(), project3.getName(), "task1", "des1", "20.02.2013", "20.02.2014");
        taskService.persistTask(project3.getUserId(), project3.getName(), "task2", "des2", "20.02.2013", "20.02.2014");

    }

    private void initEndpoints() throws Exception {
        @NotNull final Properties property = new Properties();
        property.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        @NotNull final String host = property.getProperty("host");
        @NotNull final String port = property.getProperty("port");
        Endpoint.publish("http://" + host + ":" + port + "/ProjectEndpoint", new ProjectEndpoint(this));
        Endpoint.publish("http://" + host + ":" + port + "/TaskEndpoint", new TaskEndpoint(this));
        Endpoint.publish("http://" + host + ":" + port + "/UserEndpoint", new UserEndpoint(this));
        Endpoint.publish("http://" + host + ":" + port + "/SessionEndpoint", new SessionEndpoint(this));
        Endpoint.publish("http://" + host + ":" + port + "/AdminEndpoint", new AdminEndpoint(this));
    }

    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        @Nullable final String user = "root";
        @Nullable final String password = "root";
        @Nullable final String url = "jdbc:mysql://localhost:3306/todo_list";
        @Nullable final String driver = "com.mysql.jdbc.Driver";

        final DataSource dataSource = new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        final Environment environment =
                new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
//        configuration.addMapper(UserRepository.class);
        configuration.addMapper(IProjectMapper.class);
//        configuration.addMapper(SessionRepository.class);
        configuration.addMapper(ITaskMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

}
