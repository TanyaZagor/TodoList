package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import java.util.Scanner;

public class ProjectUpdateCommand extends AbstractCommand {

    public ProjectUpdateCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "update project";
    }

    @NotNull
    @Override
    public String description() {
        return "command to update project";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String oldProjectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New description");
        @NotNull final String description = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New date start");
        @NotNull final String dateStart = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New date finish");
        @NotNull final String dateFinish = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getProjectService().merge(getServiceLocator().getCurrentUser().getId(), oldProjectName, projectName, description, dateStart, dateFinish);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
