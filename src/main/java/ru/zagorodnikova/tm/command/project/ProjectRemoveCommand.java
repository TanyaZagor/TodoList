package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {

    private Scanner in;

    public ProjectRemoveCommand() {
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
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        getServiceLocator().getProjectService().remove(getServiceLocator().getCurrentUser().getId(), projectName);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
