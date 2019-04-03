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

public class TaskSortByCreateCommand extends AbstractCommand {
    @Inject
    private TaskEndpoint taskService;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        @Nullable final List<TaskDto> tasks = taskService.sortTasksByDateCreated(serviceLocator.getSession(), projectName);
        if (tasks!= null) {
            tasks.forEach((task) -> System.out.println("Name: " + task.getName() +
                    ", Description: " + task.getDescription() + ", Date start: " + task.getDateStart() +
                    ", Date finish: " + task.getDateFinish() + ", Date create: " + task.getDateCreate() +
                    ", Status: " + task.getStatus()));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
