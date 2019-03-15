package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;
import java.util.Scanner;

public class TaskSortByStatusCommand extends AbstractCommand {

    @NotNull
    @Override
    public String command() {
        return "task sort status";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort task by status";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        @Nullable final List<Task> tasks = getServiceLocator().getTaskService().sortByStatus(getServiceLocator().getCurrentUser().getId(), projectName);
        if (tasks!= null) {
            tasks.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }

}
