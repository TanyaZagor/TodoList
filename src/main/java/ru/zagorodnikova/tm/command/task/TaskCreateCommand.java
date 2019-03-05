package ru.zagorodnikova.tm.command.task;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    private String projectName;
    private String taskName;
    private String description;
    private String dateStart;
    private String dateFinish;

    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "create task";
    }

    @Override
    public String description() {
        return "command to create task";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project name");
        projectName = in.nextLine();
        System.out.println("task name");
        taskName = in.nextLine();
        System.out.println("task description");
        description = in.nextLine();
        System.out.println("task data start");
        dateStart = in.nextLine();
        System.out.println("task data finish");
        dateFinish = in.nextLine();
        getBootstrap().getTaskService().addTask(projectName, taskName, description, dateStart, dateFinish);

    }
}
