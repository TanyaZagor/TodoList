package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("project description");
        String description = in.nextLine();
        System.out.println("project date start");
        String dateStart = in.nextLine();
        System.out.println("project date finish");
        String dateFinish = in.nextLine();
        getBootstrap().getProjectService().addProject(projectName, description, dateStart, dateFinish);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
