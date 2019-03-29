package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.TaskDto;

import java.util.List;


public class TaskSortByStartCommand extends AbstractCommand {

    @NotNull
    @Override
    public String command() {
        return "task sort start";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort task by date start";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        @Nullable final List<TaskDto> tasks = getServiceLocator().getTaskService().sortTasksByDateStart(getServiceLocator().getSession(), projectName);
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
