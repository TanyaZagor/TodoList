package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.TaskDto;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

@Component
public class TaskCreateCommand extends AbstractCommand {

    @Autowired
    private TaskEndpoint taskService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        System.out.println("task name");
        @NotNull final String taskName = terminalService.nextLine();
        System.out.println("task description");
        @NotNull final String description = terminalService.nextLine();
        System.out.println("task data start");
        @NotNull final String dateStart = terminalService.nextLine();
        System.out.println("task data finish");
        @NotNull final String dateFinish = terminalService.nextLine();
        @Nullable final TaskDto task = taskService.persistTask(serviceLocator.getSession(), projectName, taskName, description, dateStart, dateFinish);
        if (task != null) {
            System.out.println(task.getName());
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
