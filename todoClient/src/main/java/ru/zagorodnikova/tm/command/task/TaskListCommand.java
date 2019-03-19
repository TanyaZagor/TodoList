package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Task;

import java.util.List;

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
        @Nullable final List<Task> tasks = getServiceLocator().getTaskService().findAllTasksInProject(getServiceLocator().getSession().getUserId(), projectName);
        if (tasks!= null) {
            tasks.forEach(System.out::println);
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
