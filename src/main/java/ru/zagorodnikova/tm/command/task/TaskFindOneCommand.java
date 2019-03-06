package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskFindOneCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

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
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("task name");
        String taskName = in.nextLine();
        Task task = getBootstrap().getTaskService().findOne(getBootstrap().getCurrentUser().getId(), projectName, taskName);
        if (task != null) {
            System.out.println(task);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
