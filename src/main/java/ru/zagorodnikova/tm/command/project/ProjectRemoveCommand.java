package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

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
        System.out.println("project name");
        String projectName = in.nextLine();
        getBootstrap().getProjectService().deleteProject(projectName);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
