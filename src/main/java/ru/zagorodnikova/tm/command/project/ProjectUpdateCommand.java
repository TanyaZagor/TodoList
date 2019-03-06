package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import java.util.Scanner;

public class ProjectUpdateCommand extends AbstractCommand {

    private final Scanner in = getServiceLocator().getScanner();

    public ProjectUpdateCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String command() {
        return "update project";
    }

    @Override
    public String description() {
        return "command to update project";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        String oldProjectName = in.nextLine();
        System.out.println("New project name");
        String projectName = in.nextLine();
        System.out.println("New description");
        String description = in.nextLine();
        System.out.println("New date start");
        String dateStart = in.nextLine();
        System.out.println("New date finish");
        String dateFinish = in.nextLine();
        getServiceLocator().getProjectService().merge(getServiceLocator().getCurrentUser().getId(), oldProjectName, projectName, description, dateStart, dateFinish);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
