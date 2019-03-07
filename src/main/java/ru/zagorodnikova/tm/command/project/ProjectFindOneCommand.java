package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectFindOneCommand extends AbstractCommand {

    private final Scanner in = getServiceLocator().getScanner();

    public ProjectFindOneCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String command() {
        return "print one project";
    }

    @Override
    public String description() {
        return "Command to print one project";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        String projectName = in.nextLine();
        Project project = (Project) getServiceLocator().getProjectService().findOne(getServiceLocator().getCurrentUser().getId(), projectName);
        if (project != null) {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
