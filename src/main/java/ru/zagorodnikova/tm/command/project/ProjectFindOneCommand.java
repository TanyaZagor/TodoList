package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.Entity.Project;
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
        Project project = super.getBootstrap().getProjectRepositoryService().findOne(projectName);
        System.out.println(project);
    }
}
