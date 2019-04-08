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

import java.util.List;

@Component
public class TaskSortByFinishCommand extends AbstractCommand {
    @Autowired
    private TaskEndpoint taskService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

    @NotNull
    @Override
    public String command() {
        return "task sort finish";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort tasks by date finish";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        @Nullable final List<TaskDto> tasks = taskService.sortTasksByDateFinish(serviceLocator.getSession(), projectName);
        if (tasks!= null) {
            tasks.forEach(task -> System.out.println("Name: " + task.getName() +
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
