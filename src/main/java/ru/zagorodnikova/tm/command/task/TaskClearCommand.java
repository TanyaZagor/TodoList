package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.service.TaskRepositoryService;

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
        System.out.println("project id");
        String projectId = in.nextLine();
        String result = super.getBootstrap().getTaskRepositoryService().deleteAll(projectId);
        if (result != null) {
            System.out.println(result);
        }
    }
}
