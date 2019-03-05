package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskClearCommand extends AbstractCommand {

    public TaskClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "clear task";
    }

    @Override
    public String description() {
        return "command to clear tasks by project id";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project name");
        String projectName = in.nextLine();
        getBootstrap().getTaskService().deleteAll(projectName);

    }
}
