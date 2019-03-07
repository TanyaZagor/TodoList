package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskFindOneCommand extends AbstractCommand {

    private final Scanner in = getServiceLocator().getScanner();

    public TaskFindOneCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        Task task = (Task) getServiceLocator().getTaskService().findOne(getServiceLocator().getCurrentUser().getId(), projectName, taskName);
        if (task != null) {
            System.out.println(task);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
