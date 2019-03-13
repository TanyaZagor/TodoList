package ru.zagorodnikova.tm.command.task;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    private Scanner in;

    public TaskCreateCommand() {
    }

    @Override
    public String command() {
        return "create task";
    }

    @Override
    public String description() {
        return "command to create task";
    }

    @Override
    public void execute() throws IllegalArgumentException {
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("task name");
        String taskName = in.nextLine();
        System.out.println("task description");
        String description = in.nextLine();
        System.out.println("task data start");
        String dateStart = in.nextLine();
        System.out.println("task data finish");
        String dateFinish = in.nextLine();
        getServiceLocator().getTaskService().persist(getServiceLocator().getCurrentUser().getId(), projectName, taskName, description, dateStart, dateFinish);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
