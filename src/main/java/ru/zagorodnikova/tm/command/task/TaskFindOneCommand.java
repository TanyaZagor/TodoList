package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskFindOneCommand extends AbstractCommand {

    public TaskFindOneCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "print one task";
    }

    @Override
    public String description() {
        return "Command to find one task";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("task name");
        String taskName = in.nextLine();
        Task task = getBootstrap().getTaskService().findOne(projectName, taskName);
        if (task != null) {
            System.out.println(task);
        }
    }
}
