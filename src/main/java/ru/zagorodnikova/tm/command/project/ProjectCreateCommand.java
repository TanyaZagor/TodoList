package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.service.ProjectRepositoryService;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {
    private Scanner in = new Scanner(System.in);

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
        System.out.println("project id");
        String projectId = in.nextLine();
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("project description");
        String description = in.nextLine();
        System.out.println("project date finish");
        String dateFinish = in.nextLine();
        String result = super.getBootstrap().getProjectRepositoryService().addProject(projectId, projectName, description, dateFinish);
        if (result != null) {
            System.out.println(result);
        }
    }
}
