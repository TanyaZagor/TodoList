package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskClearCommand extends AbstractCommand {

    private Scanner in;

    public TaskClearCommand() {
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
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        getServiceLocator().getTaskService().removeAllInProject(getServiceLocator().getCurrentUser().getId(), projectName);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
