package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.service.ProjectRepositoryService;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {
   Scanner in = new Scanner(System.in);

    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);

    }

    @Override
    public String command() {
        return "remove project";
    }

    @Override
    public String description() {
        return "command to remove project by id";
    }

    @Override
    public void execute() {
        System.out.println("project id");
        String projectId = in.nextLine();
        String result = super.getBootstrap().getProjectRepositoryService().deleteProject(projectId);
        if (result != null) {
            System.out.println(result);
        }
    }
}
