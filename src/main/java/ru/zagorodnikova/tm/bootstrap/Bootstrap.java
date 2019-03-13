package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import ru.zagorodnikova.tm.TerminalService;
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

import java.util.*;

@Setter
@Getter
public class Bootstrap implements ServiceLocator {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, AbstractCommand> commands = new HashMap<>();
    private final IProjectRepository<AbstractEntity> projectRepository = new ProjectRepository();
    private final ITaskRepository<AbstractEntity> taskRepository = new TaskRepository();
    private final IUserRepository<AbstractEntity> userRepository = new UserRepository();
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);
    private final ITaskService taskService = new TaskService(taskRepository, projectRepository);
    private final IUserService userService = new UserService(userRepository);
    private final TerminalService terminalService = new TerminalService(this);
    private User currentUser;


    public void init(Class[] commandClasses) {

        this.initCommands(this, commandClasses);
        this.initProjectsAndUsers();

        this.start();
    }

    private void start() {
        terminalService.start();
    }

    public void execute(String command) {
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
        User user1 = (User) userService.signUp("login", "password", "first name", "last name", "email@email.ru", RoleType.USER);
        User user2 = (User) userService.signUp("login2", "password2", "first name", "last name", "email@email.ru", RoleType.ADMIN);

        Project project1 = (Project) projectService.persist(user1.getId(), "Project1", "Description1", "20.02.2019", "20.05.2019");
        Project project2 = (Project) projectService.persist(user2.getId(), "Project2", "Description2", "20.05.2019", "20.06.2019");
        Project project3 = (Project) projectService.persist(user2.getId(), "Project1", "Description1", "20.02.2016", "20.05.2020");
        project3.setStatus(Status.DONE);

        taskService.persist(project1.getUserId(), project1.getName(), "task1", "des1", "20.02.2012", "20.02.2013");
        taskService.persist(project1.getUserId(), project1.getName(), "task2", "des2", "20.02.2010", "20.02.2011");
        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persist(project3.getUserId(), project3.getName(), "task1", "des1", "20.02.2013", "20.02.2014");
        taskService.persist(project3.getUserId(), project3.getName(), "task2", "des2", "20.02.2013", "20.02.2014");

    }

    private void initCommands(Bootstrap bootstrap, Class[] commandClasses) {

        addCommand(commandClasses, bootstrap);

    }

    private void addCommand(Class[] commandClasses, ServiceLocator bootstrap){
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
