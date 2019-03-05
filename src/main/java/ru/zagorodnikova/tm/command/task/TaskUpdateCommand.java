package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import java.util.Scanner;

public class TaskUpdateCommand extends AbstractCommand {

    public TaskUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "update task";
    }

    @Override
    public String description() {
        return "command to update task";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("task name");
        String oldTaskName = in.nextLine();
        System.out.println("New task name");
        String taskName = in.nextLine();
        System.out.println("New description");
        String description = in.nextLine();
        System.out.println("New date start");
        String dateStart = in.nextLine();
        System.out.println("New date finish");
        String dateFinish = in.nextLine();
        getBootstrap().getTaskService().updateTask(projectName, oldTaskName, taskName, description, dateStart, dateFinish);

    }
}
