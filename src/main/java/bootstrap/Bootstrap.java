package bootstrap;

import command.AbstractCommand;
import command.project.ProjectClearCommand;
import command.project.ProjectCreateCommand;
import command.project.ProjectListCommand;
import command.project.ProjectRemoveCommand;
import command.system.HelpCommand;
import command.task.TaskClearCommand;
import command.task.TaskCreateCommand;
import command.task.TaskListCommand;
import command.task.TaskRemoveCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

 public class Bootstrap {
    private Scanner scanner = new Scanner(System.in);
    public static Map<String, AbstractCommand> commands;

    public void init() {
         commands = new HashMap<>();

        ProjectClearCommand projectClearCommand = new ProjectClearCommand();
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand();
        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand();
        ProjectListCommand projectListCommand = new ProjectListCommand();

        TaskClearCommand taskClearCommand = new TaskClearCommand();
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand();
        TaskListCommand taskListCommand = new TaskListCommand();
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand();

        HelpCommand helpCommand = new HelpCommand();

        commands.put(projectClearCommand.command(), projectClearCommand);
        commands.put(projectRemoveCommand.command(), projectRemoveCommand);
        commands.put(projectCreateCommand.command(), projectCreateCommand);
        commands.put(projectListCommand.command(), projectListCommand);

        commands.put(taskClearCommand.command(), taskClearCommand);
        commands.put(taskCreateCommand.command(), taskCreateCommand);
        commands.put(taskListCommand.command(), taskListCommand);
        commands.put(taskRemoveCommand.command(), taskRemoveCommand);

        commands.put(helpCommand.command(), helpCommand);

        start();
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
        AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        abstractCommand.execute();
    }

}
