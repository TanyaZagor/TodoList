package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectFindOneCommand extends AbstractCommand {

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
        Scanner in = new Scanner(System.in);
        System.out.println("project name");
        String projectName = in.nextLine();
        Project project = getBootstrap().getProjectService().findOne(projectName);
        if (project != null) {
            System.out.println(project);
        }
    }
}
