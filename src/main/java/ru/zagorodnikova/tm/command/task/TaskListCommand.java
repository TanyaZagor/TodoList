package ru.zagorodnikova.tm.command.task;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.List;
import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private final Scanner in = getServiceLocator().getScanner();

    public TaskListCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        System.out.println("project name");
        String projectName = in.nextLine();
        List<AbstractEntity> tasks = getServiceLocator().getTaskService().findAll(getServiceLocator().getCurrentUser().getId(), projectName);
        if (tasks.size() > 0) {
            tasks.forEach(System.out::println);
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
