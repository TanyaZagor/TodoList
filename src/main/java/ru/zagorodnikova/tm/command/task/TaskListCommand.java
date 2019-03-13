package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.List;
import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    public TaskListCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "task list";
    }

    @NotNull
    @Override
    public String description() {
        return "command to print out task list";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        @Nullable final List<AbstractEntity> tasks = getServiceLocator().getTaskService().findAll(getServiceLocator().getCurrentUser().getId(), projectName);
        if (tasks!= null) {
            tasks.forEach(System.out::println);
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
