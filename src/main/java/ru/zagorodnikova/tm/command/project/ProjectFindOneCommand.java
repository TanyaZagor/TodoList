package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectFindOneCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

    public ProjectFindOneCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        Project project = getBootstrap().getProjectService().findOne(getBootstrap().getCurrentUser().getId(), projectName);
        if (project != null) {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
