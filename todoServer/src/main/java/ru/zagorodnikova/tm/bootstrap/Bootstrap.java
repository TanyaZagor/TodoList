package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.*;
import ru.zagorodnikova.tm.endpoint.*;
import ru.zagorodnikova.tm.entity.*;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.SessionRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.repository.UserRepository;
import ru.zagorodnikova.tm.service.*;

import javax.xml.ws.Endpoint;
import java.util.Properties;

@Setter
@Getter
public class Bootstrap implements ServiceLocator {

    @NotNull private final IProjectRepository<Project> projectRepository = new ProjectRepository();
    @NotNull private final ITaskRepository<Task> taskRepository = new TaskRepository();
    @NotNull private final IUserRepository<User> userRepository = new UserRepository();
    @NotNull private final SessionRepository sessionRepository = new SessionRepository();
    @NotNull private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);
    @NotNull private final ITaskService taskService = new TaskService(taskRepository, projectRepository);
    @NotNull private final IUserService userService = new UserService(userRepository);
    @NotNull private final ISessionService sessionService = new SessionService(sessionRepository);
    @NotNull private final IDomainService domainService = new DomainService(projectRepository, taskRepository, userRepository);
    @NotNull private final IAdminService adminService = new AdminService(userRepository);


    public void init() throws Exception {
        initProjectsAndUsers();
        initEndpoints();
    }


    private void initProjectsAndUsers() throws Exception {
        final User user1 = userService.signUp("login", "password", "first name", "last name", "email@email.ru");
        final User user2 = userService.signUp("admin", "admin", "first name", "last name", "email@email.ru");
        user2.setRoleType(RoleType.ADMIN);

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


}
