package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.List;
import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private Scanner in;

    public TaskListCommand() {
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
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        List<AbstractEntity> tasks = getServiceLocator().getTaskService().findAll(getServiceLocator().getCurrentUser().getId(), projectName);
        if (tasks!= null) {
            tasks.forEach(System.out::println);
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
