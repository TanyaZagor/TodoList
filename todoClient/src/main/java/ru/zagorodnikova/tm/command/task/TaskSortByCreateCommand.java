package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.service.Task;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.List;

public class TaskSortByCreateCommand extends AbstractCommand {

    @NotNull
    @Override
    public String command() {
        return "task sort create";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort task by date created";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        @Nullable final List<Task> tasks = getServiceLocator().getTaskService().sortTasksByDateCreated(getServiceLocator().getCurrentUser().getId(), projectName);
        if (tasks!= null) {
            tasks.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}