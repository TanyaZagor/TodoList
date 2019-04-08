package ru.zagorodnikova.tm.command.task;

import org.springframework.stereotype.Component;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

@Component
public class TaskClearCommand extends AbstractCommand {

    @Autowired
    private TaskEndpoint taskService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

    @NotNull
    @Override
    public String command() {
        return "clear task";
    }

    @NotNull
    @Override
    public String description() {
        return "command to clear tasks by project id";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        taskService.removeAllTasksInProject(serviceLocator.getSession(), projectName);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
