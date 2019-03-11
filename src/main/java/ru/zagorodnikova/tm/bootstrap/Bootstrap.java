package ru.zagorodnikova.tm.bootstrap;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.command.system.AboutCommand;
import ru.zagorodnikova.tm.command.user.*;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.command.project.*;
import ru.zagorodnikova.tm.command.system.HelpCommand;
import ru.zagorodnikova.tm.command.task.*;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.repository.UserRepository;
import ru.zagorodnikova.tm.service.ProjectService;
import ru.zagorodnikova.tm.service.TaskService;
import ru.zagorodnikova.tm.service.UserService;

import java.util.*;

public class Bootstrap implements ServiceLocator {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, AbstractCommand> commands = new HashMap<>();
    private final IProjectRepository<AbstractEntity> projectRepository = new ProjectRepository();
    private final ITaskRepository<AbstractEntity> taskRepository = new TaskRepository();
    private final IUserRepository<AbstractEntity> userRepository = new UserRepository();
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);
    private final ITaskService taskService = new TaskService(taskRepository, projectRepository);
    private final IUserService userService = new UserService(userRepository);
    private User currentUser;


    public void init(Bootstrap bootstrap) {

        bootstrap.initCommands(bootstrap);
        bootstrap.initProjectsAndUsers();

        bootstrap.start();
    }

    private void start() {
        System.out.println("Welcome");
        String command = "";
        while (!"exit".equals(command)) {
            System.out.println("Command: ");
            command = scanner.nextLine();
            execute(command);
        }
    }

    private void execute(String command) {
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
        Project project3 = (Project) projectService.persist(user2.getId(), "Project1", "Description1", "20.02.2016", "20.05.2016");

        taskService.persist(project1.getUserId(), project1.getName(), "task1", "des1", "20.02.2013", "20.02.2014");
        taskService.persist(project1.getUserId(), project1.getName(), "task2", "des2", "20.02.2013", "20.02.2014");
        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
        taskService.persist(project3.getUserId(), project3.getName(), "task1", "des1", "20.02.2013", "20.02.2014");
        taskService.persist(project3.getUserId(), project3.getName(), "task2", "des2", "20.02.2013", "20.02.2014");

    }

    private void initCommands(Bootstrap bootstrap) {

        addCommand(new ProjectCreateCommand(bootstrap));
        addCommand(new ProjectClearCommand(bootstrap));
        addCommand(new ProjectFindOneCommand(bootstrap));
        addCommand(new ProjectListCommand(bootstrap));
        addCommand(new ProjectRemoveCommand(bootstrap));
        addCommand(new ProjectUpdateCommand(bootstrap));

        addCommand(new TaskCreateCommand(bootstrap));
        addCommand(new TaskClearCommand(bootstrap));
        addCommand(new TaskFindOneCommand(bootstrap));
        addCommand(new TaskListCommand(bootstrap));
        addCommand(new TaskRemoveCommand(bootstrap));
        addCommand(new TaskUpdateCommand(bootstrap));

        addCommand(new UserSignUpCommand(bootstrap));
        addCommand(new UserSignInCommand(bootstrap));
        addCommand(new UserSignOutCommand(bootstrap));
        addCommand(new UserChangePasswordCommand(bootstrap));
        addCommand(new UserShowCommand(bootstrap));
        addCommand(new UserUpdateCommand(bootstrap));
        addCommand(new UserClearCommand(bootstrap));
        addCommand(new UserRemoveCommand(bootstrap));
        addCommand(new UserListCommand(bootstrap));

        addCommand(new HelpCommand(bootstrap));
        addCommand(new AboutCommand(bootstrap));

    }

    private void addCommand(AbstractCommand command) {
        commands.put(command.command(), command);
    }

    private boolean isAuth() {
        return currentUser != null;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public IUserService getUserService() {
        return userService;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
