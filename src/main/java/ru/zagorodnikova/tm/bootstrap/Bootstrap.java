package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.service.TerminalService;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.*;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.repository.UserRepository;
import ru.zagorodnikova.tm.service.ProjectService;
import ru.zagorodnikova.tm.service.TaskService;
import ru.zagorodnikova.tm.service.UserService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

@Setter
@Getter
public class Bootstrap implements ServiceLocator {

    @NotNull private final Map<String, AbstractCommand> commands = new HashMap<>();
    @NotNull private final IProjectRepository<Project> projectRepository = new ProjectRepository();
    @NotNull private final ITaskRepository<Task> taskRepository = new TaskRepository();
    @NotNull private final IUserRepository<User> userRepository = new UserRepository();
    @NotNull private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);
    @NotNull private final ITaskService taskService = new TaskService(taskRepository, projectRepository);
    @NotNull private final IUserService userService = new UserService(userRepository);
    @NotNull private final TerminalService terminalService = new TerminalService(this);
    @Nullable private User currentUser;


    public void init(@NotNull Class[] commandClasses) {
        this.initCommands(this, commandClasses);
        this.initProjectsAndUsers();

        this.start();
    }

    private void start() {
        terminalService.start();
    }

    public void execute(@Nullable String command) throws IOException, JAXBException, ClassNotFoundException {
        if (command == null || command.isEmpty()) return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()) {
            if (isAuth()) {
                abstractCommand.execute();
            }
        } else {
            abstractCommand.execute();
        }

    }

    private void initProjectsAndUsers() {
        final User user1 = userService.signUp("login", "password", "first name", "last name", "email@email.ru");
        final User user2 = userService.signUp("login2", "password2", "first name", "last name", "email@email.ru");
        user2.setRoleType(RoleType.ADMIN);

        final Project project1 = projectService.persist(user1.getId(), "Project1", "Description1", "20.02.2019", "20.05.2019");
        final Project project2 = projectService.persist(user2.getId(), "Project2", "Description2", "20.05.2019", "20.06.2019");
        final Project project3 = projectService.persist(user2.getId(), "Project1", "Description1", "20.02.2016", "20.05.2020");
        project3.setStatus(Status.DONE);

        taskService.persist(project1.getUserId(), project1.getName(), "task1", "des1", "20.02.2012", "20.02.2013");
        taskService.persist(project1.getUserId(), project1.getName(), "task2", "des2", "20.02.2010", "20.02.2011");
        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persist(project3.getUserId(), project3.getName(), "task1", "des1", "20.02.2013", "20.02.2014");
        taskService.persist(project3.getUserId(), project3.getName(), "task2", "des2", "20.02.2013", "20.02.2014");

    }

    private void initCommands(@NotNull Bootstrap bootstrap,@NotNull Class[] commandClasses) {

        addCommand(commandClasses, bootstrap);

    }

    private void addCommand(@NotNull Class[] commandClasses, @NotNull ServiceLocator bootstrap){
        for (Class commandClass : commandClasses) {
            if (commandClass.getSuperclass().equals(AbstractCommand.class)) {
                AbstractCommand abstractCommand = null;
                try {
                    abstractCommand = (AbstractCommand) commandClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (abstractCommand != null) {
                    abstractCommand.setServiceLocator(bootstrap);
                    commands.put(abstractCommand.command(), abstractCommand);
                }
            }
        }
    }

    private boolean isAuth() {
        return currentUser != null;
    }

}
