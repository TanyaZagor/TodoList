package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.Task;

public class TaskFindOneCommand extends AbstractCommand {

    public TaskFindOneCommand(){
    }

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
    public void execute() throws Exception_Exception {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("task name");
        @NotNull final String taskName = getServiceLocator().getTerminalService().nextLine();
        @Nullable final Task task = getServiceLocator().getTaskService().findOneTask(getServiceLocator().getSession(), projectName, taskName);
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
