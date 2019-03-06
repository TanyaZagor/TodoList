package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "task list";
    }

    @Override
    public String description() {
        return "command to print out task list";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        String projectName = in.nextLine();
        List<Task> tasks = getBootstrap().getTaskService().findAll(getBootstrap().getCurrentUser().getId(), projectName);
        if (tasks.size() > 0) {
            tasks.forEach(System.out::println);
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
