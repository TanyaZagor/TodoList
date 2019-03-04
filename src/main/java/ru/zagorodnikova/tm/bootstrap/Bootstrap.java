package ru.zagorodnikova.tm.bootstrap;

import ru.zagorodnikova.tm.Entity.Project;
import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.command.project.*;
import ru.zagorodnikova.tm.command.system.HelpCommand;
import ru.zagorodnikova.tm.command.task.*;
import ru.zagorodnikova.tm.service.ProjectRepositoryService;
import ru.zagorodnikova.tm.service.TaskRepositoryService;

import java.util.*;

public class Bootstrap {

    private static Bootstrap bootstrap = null;

    public static Map<String, AbstractCommand> commands;
    public static Map<String, Project> projects = new LinkedHashMap<>();
    public static Map<String, Task> tasks = new LinkedHashMap<>();
    public static ProjectRepositoryService projectRepositoryService;
    public static TaskRepositoryService taskRepositoryService;

    private Bootstrap() {

    }

    public static Bootstrap getBootstrap() {
        if (bootstrap == null) {
            bootstrap = new Bootstrap();
        }
        return bootstrap;
    }

    public static void init() {
        Bootstrap bootstrap = getBootstrap();
        projectRepositoryService = new ProjectRepositoryService();
        taskRepositoryService = new TaskRepositoryService();
        commands = new HashMap<>();

        ProjectClearCommand projectClearCommand = new ProjectClearCommand(bootstrap);
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(bootstrap);
        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(bootstrap);
        ProjectListCommand projectListCommand = new ProjectListCommand(bootstrap);
        ProjectUpdateCommand projectUpdateCommand = new ProjectUpdateCommand(bootstrap);

        TaskClearCommand taskClearCommand = new TaskClearCommand(bootstrap);
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand(bootstrap);
        TaskListCommand taskListCommand = new TaskListCommand(bootstrap);
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(bootstrap);
        TaskUpdateCommand taskUpdateCommand = new TaskUpdateCommand(bootstrap);

        HelpCommand helpCommand = new HelpCommand(bootstrap);

        commands.put(projectClearCommand.command(), projectClearCommand);
        commands.put(projectRemoveCommand.command(), projectRemoveCommand);
        commands.put(projectCreateCommand.command(), projectCreateCommand);
        commands.put(projectListCommand.command(), projectListCommand);
        commands.put(projectUpdateCommand.command(), projectUpdateCommand);

        commands.put(taskClearCommand.command(), taskClearCommand);
        commands.put(taskCreateCommand.command(), taskCreateCommand);
        commands.put(taskListCommand.command(), taskListCommand);
        commands.put(taskRemoveCommand.command(), taskRemoveCommand);
        commands.put(taskUpdateCommand.command(), taskUpdateCommand);

        commands.put(helpCommand.command(), helpCommand);

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

        start();
    }

    private static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome");
        String command = "";
        while (!"exit".equals(command)) {
            System.out.println("Command: ");
            command = scanner.nextLine();
            execute(command);
        }
    }

    private static void execute(String command) {
        if (command == null || command.isEmpty()) return;
        AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        abstractCommand.execute();
    }



    public ProjectRepositoryService getProjectRepositoryService() {
        return projectRepositoryService;
    }

    public void setProjectRepositoryService(ProjectRepositoryService projectRepositoryService) {
        this.projectRepositoryService = projectRepositoryService;
    }

    public TaskRepositoryService getTaskRepositoryService() {
        return taskRepositoryService;
    }

    public void setTaskRepositoryService(TaskRepositoryService taskRepositoryService) {
        this.taskRepositoryService = taskRepositoryService;
    }
}
