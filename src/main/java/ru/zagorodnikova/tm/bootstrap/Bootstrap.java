package ru.zagorodnikova.tm.bootstrap;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.command.project.*;
import ru.zagorodnikova.tm.command.system.HelpCommand;
import ru.zagorodnikova.tm.command.task.*;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.service.ProjectService;
import ru.zagorodnikova.tm.service.TaskService;

import java.util.*;

public class Bootstrap {

    private static Bootstrap bootstrap;

    private static final Map<String, AbstractCommand> commands = new HashMap<>();
    private static final Map<String, Project> projects = new LinkedHashMap<>();
    private static final Map<String, Task> tasks = new LinkedHashMap<>();
    private static final ProjectRepository projectRepository = new ProjectRepository();
    private static final TaskRepository taskRepository = new TaskRepository();
    private static final ProjectService projectService = new ProjectService(projectRepository);
    private static final TaskService taskService = new TaskService(taskRepository);

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
        bootstrap.initProjects();

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
        abstractCommand.execute();
    }

    private void initProjects() {
        Project project1 = new Project( "Project1", "Description1", "20.02.2019", "20.05.2019");
        Project project2 = new Project( "Project2", "Description2", "20.05.2019", "20.06.2019");
        Task task1 = new Task(project1.getId(), "task1", "des1", "20.02.2013", "20.02.2014");
        Task task2 = new Task(project1.getId(), "task2", "des2", "20.02.2013", "20.02.2014");
        Task task3 = new Task(project2.getId(), "task3", "des3", "20.02.2013", "20.02.2014");
        tasks.put(task1.getId(), task1);
        tasks.put(task2.getId(), task2);
        tasks.put(task3.getId(), task3);
        projects.put(project1.getId(), project1);
        projects.put(project2.getId(), project2);
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

        commands.put(helpCommand.command(), helpCommand);
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
}
