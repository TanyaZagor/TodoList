package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class TaskRemoveCommand extends AbstractCommand {

    public TaskRemoveCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "remove task";
    }

    @NotNull
    @Override
    public String description() {
        return "command to remove task";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("task name");
        @NotNull final String taskName = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getTaskService().removeTask(getServiceLocator().getSession().getUserId(), projectName, taskName);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
