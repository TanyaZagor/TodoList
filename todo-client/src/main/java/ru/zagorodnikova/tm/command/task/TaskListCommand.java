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
import java.util.List;

public class TaskListCommand extends AbstractCommand {

    @Inject
    private TaskEndpoint taskService;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        @Nullable final List<TaskDto> tasks = taskService.findAllTasksInProject(serviceLocator.getSession(), projectName);
        if (tasks!= null) {
            tasks.forEach((v) -> System.out.println(v.getName()));
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
