package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskFindOneCommand extends AbstractCommand {

    private Scanner in;

    public TaskFindOneCommand(){
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
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("task name");
        String taskName = in.nextLine();
        System.out.println("task description");
        String taskDescription = in.nextLine();
        Task task = (Task) getServiceLocator().getTaskService().findOne(getServiceLocator().getCurrentUser().getId(), projectName, taskName, taskDescription);
        if (task != null) {
            System.out.println(task);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
