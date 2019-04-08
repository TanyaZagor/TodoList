package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

@Component
public class TaskRemoveCommand extends AbstractCommand {

    @Autowired
    private TaskEndpoint taskService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        System.out.println("task name");
        @NotNull final String taskName = terminalService.nextLine();
        taskService.removeTask(serviceLocator.getSession(), projectName, taskName);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
