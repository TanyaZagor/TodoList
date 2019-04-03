package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.TaskDto;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.inject.Inject;

public class TaskFindOneCommand extends AbstractCommand {

    @Inject
    private TaskEndpoint taskService;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TerminalService terminalService;

    @NotNull
    @Override
    public String command() {
        return "print one task";
    }

    @NotNull
    @Override
    public String description() {
        return "Command to find one task";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        System.out.println("task name");
        @NotNull final String taskName = terminalService.nextLine();
        @Nullable final TaskDto task = taskService.findOneTask(serviceLocator.getSession(), projectName, taskName);
        if (task != null) {
            System.out.println("Name: " + task.getName() + ", Description: " + task.getDescription() +
                    ", Date start: " + task.getDateStart() + ", Date finish: " + task.getDateFinish() +
                    ", Date create: " + task.getDateCreate() + ", Status: " + task.getStatus());
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
