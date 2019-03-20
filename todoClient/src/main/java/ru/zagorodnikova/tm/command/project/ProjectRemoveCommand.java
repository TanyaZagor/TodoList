package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class ProjectRemoveCommand extends AbstractCommand {

    public ProjectRemoveCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "remove project";
    }

    @NotNull
    @Override
    public String description() {
        return "command to remove project by id";
    }

    @Override
    public void execute() {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getProjectService().removeProject(getServiceLocator().getSession(), projectName);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
