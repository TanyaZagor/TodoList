package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectFindOneCommand extends AbstractCommand {

    public ProjectFindOneCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "print one project";
    }

    @NotNull
    @Override
    public String description() {
        return "Command to print one project";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("project description");
        @NotNull final String projectDescription = getServiceLocator().getTerminalService().nextLine();
        @Nullable final Project project = getServiceLocator().getProjectService().findOne(getServiceLocator().getCurrentUser().getId(), projectName, projectDescription);
        if (project != null) {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
