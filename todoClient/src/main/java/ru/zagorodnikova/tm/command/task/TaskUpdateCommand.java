package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class TaskUpdateCommand extends AbstractCommand {

    public TaskUpdateCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "update task";
    }

    @NotNull
    @Override
    public String description() {
        return "command to update task";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("task name");
        @NotNull final String oldTaskName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New task name");
        @NotNull final String taskName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New description");
        @NotNull final String description = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New date start");
        @NotNull final String dateStart = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New date finish");
        @NotNull final String dateFinish = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getTaskService().mergeTask(getServiceLocator().getSession(), projectName, oldTaskName, taskName, description, dateStart, dateFinish);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
