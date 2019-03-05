package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.service.TaskRepositoryService;
import java.util.Scanner;

public class TaskRemoveCommand extends AbstractCommand {

    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "remove task";
    }

    @Override
    public String description() {
        return "command to remove task";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("task name");
        String taskName = in.nextLine();
        String result = super.getBootstrap().getTaskRepositoryService().deleteTask(projectName, taskName);
        if (result != null) {
            System.out.println(result);
        }
    }
}
