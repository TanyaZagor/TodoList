package ru.zagorodnikova.tm.bootstrap;

import ru.zagorodnikova.tm.command.user.*;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.Task;
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

public class Bootstrap {

    private static Bootstrap bootstrap;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, AbstractCommand> commands = new HashMap<>();
    private static final Map<String, Project> projects = new LinkedHashMap<>();
    private static final Map<String, Task> tasks = new LinkedHashMap<>();
    private static final Map<String, User> users = new LinkedHashMap<>();
    private static final ProjectRepository projectRepository = new ProjectRepository();
    private static final TaskRepository taskRepository = new TaskRepository();
    private static final UserRepository userRepository = new UserRepository();
    private static final ProjectService projectService = new ProjectService(projectRepository);
    private static final TaskService taskService = new TaskService(taskRepository);
    private static final UserService userService = new UserService(userRepository);
    private static User currentUser;

    private Bootstrap() {
    }

    public static Bootstrap getBootstrap() {
        if (bootstrap != null) {
            return bootstrap;
        }
        return new Bootstrap();
    }

    public static void init() {
        Bootstrap bootstrap = getBootstrap();

        bootstrap.initCommands(bootstrap);
        bootstrap.initProjectsAndUsers();

        bootstrap.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
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
        AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()) {
            if (currentUser != null) {
                abstractCommand.execute();
            }
        } else {
            abstractCommand.execute();
        }

    }

    private void initProjectsAndUsers() {
        User user1 = new User("login", "password", "first name", "last name", "email@email.ru");
        User user2 = new User("login2", "password2", "first name", "last name", "email@email.ru");
        user2.setRoleType(RoleType.ADMIN);

        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);

        Project project1 = new Project(user1.getId(), "Project1", "Description1", "20.02.2019", "20.05.2019");
        Project project2 = new Project(user2.getId(), "Project2", "Description2", "20.05.2019", "20.06.2019");
        Project project3 = new Project(user2.getId(), "Project1", "Description1", "20.02.2019", "20.05.2019");
        Task task1 = new Task(project1.getUserId(), project1.getId(), "task1", "des1", "20.02.2013", "20.02.2014");
        Task task2 = new Task(project1.getUserId(), project1.getId(), "task2", "des2", "20.02.2013", "20.02.2014");
        Task task3 = new Task(project2.getUserId(), project2.getId(), "task3", "des3", "20.02.2013", "20.02.2014");
        Task task10 = new Task(project3.getUserId(), project3.getId(), "task1", "des1", "20.02.2013", "20.02.2014");
        Task task20 = new Task(project3.getUserId(), project3.getId(), "task2", "des2", "20.02.2013", "20.02.2014");
        Task task30 = new Task(project2.getUserId(), project2.getId(), "task3", "des3", "20.02.2013", "20.02.2014");
        tasks.put(task1.getId(), task1);
        tasks.put(task2.getId(), task2);
        tasks.put(task3.getId(), task3);
        tasks.put(task10.getId(), task10);
        tasks.put(task20.getId(), task20);
        tasks.put(task30.getId(), task30);
        projects.put(project1.getId(), project1);
        projects.put(project2.getId(), project2);
        projects.put(project3.getId(), project3);
    }

    private void initCommands(Bootstrap bootstrap) {

        ProjectClearCommand projectClearCommand = new ProjectClearCommand(bootstrap);
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(bootstrap);
        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(bootstrap);
        ProjectListCommand projectListCommand = new ProjectListCommand(bootstrap);
        ProjectUpdateCommand projectUpdateCommand = new ProjectUpdateCommand(bootstrap);
        ProjectFindOneCommand projectFindOneCommand = new ProjectFindOneCommand(bootstrap);

        TaskClearCommand taskClearCommand = new TaskClearCommand(bootstrap);
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand(bootstrap);
        TaskListCommand taskListCommand = new TaskListCommand(bootstrap);
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(bootstrap);
        TaskUpdateCommand taskUpdateCommand = new TaskUpdateCommand(bootstrap);
        TaskFindOneCommand taskFindOneCommand = new TaskFindOneCommand(bootstrap);

        UserChangePasswordCommand userChangePassword = new UserChangePasswordCommand(bootstrap);
        UserSignInCommand userSignInCommand = new UserSignInCommand(bootstrap);
        UserSignUpCommand userSignUpCommand = new UserSignUpCommand(bootstrap);
        UserSignOutCommand userSignOutCommand = new UserSignOutCommand(bootstrap);
        UserShowCommand userShowCommand = new UserShowCommand(bootstrap);
        UserUpdateCommand userUpdateCommand = new UserUpdateCommand(bootstrap);

        HelpCommand helpCommand = new HelpCommand(bootstrap);

        commands.put(projectClearCommand.command(), projectClearCommand);
        commands.put(projectRemoveCommand.command(), projectRemoveCommand);
        commands.put(projectCreateCommand.command(), projectCreateCommand);
        commands.put(projectListCommand.command(), projectListCommand);
        commands.put(projectUpdateCommand.command(), projectUpdateCommand);
        commands.put(projectFindOneCommand.command(), projectFindOneCommand);

        commands.put(taskClearCommand.command(), taskClearCommand);
        commands.put(taskCreateCommand.command(), taskCreateCommand);
        commands.put(taskListCommand.command(), taskListCommand);
        commands.put(taskRemoveCommand.command(), taskRemoveCommand);
        commands.put(taskUpdateCommand.command(), taskUpdateCommand);
        commands.put(taskFindOneCommand.command(), taskFindOneCommand);

        commands.put(userChangePassword.command(), userChangePassword);
        commands.put(userSignInCommand.command(), userSignInCommand);
        commands.put(userSignOutCommand.command(), userSignOutCommand);
        commands.put(userSignUpCommand.command(), userSignUpCommand);
        commands.put(userShowCommand.command(), userShowCommand);
        commands.put(userUpdateCommand.command(), userUpdateCommand);

        commands.put(helpCommand.command(), helpCommand);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public Map<String, Project> getProjects() {
        return projects;
    }

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public UserService getUserService() {
        return userService;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Bootstrap.currentUser = currentUser;
    }
}
