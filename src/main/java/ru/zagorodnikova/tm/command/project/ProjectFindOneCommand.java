package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectFindOneCommand extends AbstractCommand {

    private Scanner in;

    public ProjectFindOneCommand() {
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
        in = getServiceLocator().getScanner();
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
