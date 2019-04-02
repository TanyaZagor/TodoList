package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class TaskClearCommand extends AbstractCommand {

    public TaskClearCommand() {
    }

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
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getTaskService().removeAllTasksInProject(getServiceLocator().getSession(), projectName);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
