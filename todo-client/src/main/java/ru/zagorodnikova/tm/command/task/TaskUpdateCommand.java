package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.inject.Inject;

public class TaskUpdateCommand extends AbstractCommand {

    @Inject
    private TaskEndpoint taskService;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        System.out.println("task name");
        @NotNull final String oldTaskName = terminalService.nextLine();
        System.out.println("New task name");
        @NotNull final String taskName = terminalService.nextLine();
        System.out.println("New description");
        @NotNull final String description = terminalService.nextLine();
        System.out.println("New date start");
        @NotNull final String dateStart = terminalService.nextLine();
        System.out.println("New date finish");
        @NotNull final String dateFinish = terminalService.nextLine();
        System.out.println("Status");
        @NotNull final String status = terminalService.nextLine();
        taskService.mergeTask(serviceLocator.getSession(), projectName, oldTaskName, taskName, description, dateStart, dateFinish, status);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
