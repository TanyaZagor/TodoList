package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import java.util.Scanner;

public class TaskRemoveCommand extends AbstractCommand {

    private Scanner in;

    public TaskRemoveCommand() {
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
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("task name");
        String taskName = in.nextLine();
        getServiceLocator().getTaskService().remove(getServiceLocator().getCurrentUser().getId(), projectName, taskName);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
