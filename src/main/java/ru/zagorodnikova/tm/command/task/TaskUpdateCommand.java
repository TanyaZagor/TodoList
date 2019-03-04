package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.service.TaskRepositoryService;

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
        System.out.println("project id");
        String projectId = in.nextLine();
        System.out.println("task id");
        String taskId = in.nextLine();
        System.out.println("Update: 1 - name\n2 - description\n3 - date start\n4 - date finish");
        String updateId = in.nextLine();
        System.out.println("New: ");
        String newData = in.nextLine();
        String result = super.getBootstrap().getTaskRepositoryService().updateTask(projectId, taskId, updateId, newData);
        if (result != null) {
            System.out.println(result);
        }
    }
}
