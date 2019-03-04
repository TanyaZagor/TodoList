package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.Entity.Task;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Map;
import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {


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
        Scanner in = new Scanner(System.in);
        System.out.println("project id");
        String projectId = in.nextLine();
        Map<String, Task> tasks = super.getBootstrap().getTaskRepositoryService().print(projectId);
        if (tasks != null) {
            tasks.forEach((k, v) -> System.out.println(v));
        } else {
            System.out.println("Wrong id");
        }

    }
}
