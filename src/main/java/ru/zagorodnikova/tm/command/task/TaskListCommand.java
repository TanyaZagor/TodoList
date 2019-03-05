package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.entity.Task;
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
        System.out.println("project name");
        String projectName = in.nextLine();
        Map<String, Task> tasks = getBootstrap().getTaskService().print(projectName);
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("Wrong name");
        } else {
            tasks.forEach((k, v) -> System.out.println(v));
        }

    }
}
