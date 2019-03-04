package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.service.ProjectRepositoryService;

import java.util.Scanner;

public class ProjectUpdateCommand extends AbstractCommand {


    public ProjectUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        Scanner in = new Scanner(System.in);
        System.out.println("project id");
        String projectId = in.nextLine();
        System.out.println("Update: 1 - name\n2 - description\n3 - date finish");
        String updateId = in.nextLine();
        System.out.println("New: ");
        String newData = in.nextLine();
        String result = super.getBootstrap().getProjectRepositoryService().updateProject(projectId, updateId, newData);
        if (result != null) {
            System.out.println(result);
        }
    }
}
