package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class TaskCreateCommand extends AbstractCommand {

    public TaskCreateCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "create task";
    }

    @NotNull
    @Override
    public String description() {
        return "command to create task";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("task name");
        @NotNull final String taskName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("task description");
        @NotNull final String description = getServiceLocator().getTerminalService().nextLine();
        System.out.println("task data start");
        @NotNull final String dateStart = getServiceLocator().getTerminalService().nextLine();
        System.out.println("task data finish");
        @NotNull final String dateFinish = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getTaskService().persistTask(getServiceLocator().getCurrentUser().getId(), projectName, taskName, description, dateStart, dateFinish);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
