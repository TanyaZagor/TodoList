package ru.zagorodnikova.tm.command.project;


import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.Project;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {

    private Scanner in;

    public ProjectCreateCommand() {
    }

    @Override
    public String command() {
        return "create project";
    }

    @Override
    public String description() {
        return "command to create project";
    }

    @Override
    public void execute() {
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("project description");
        String description = in.nextLine();
        System.out.println("project date start");
        String dateStart = in.nextLine();
        System.out.println("project date finish");
        String dateFinish = in.nextLine();
        Project project = (Project) getServiceLocator().getProjectService().persist(getServiceLocator().getCurrentUser().getId(), projectName, description, dateStart, dateFinish);
        System.out.println(project);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
