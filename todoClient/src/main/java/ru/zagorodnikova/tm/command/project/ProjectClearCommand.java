package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;


public class ProjectClearCommand extends AbstractCommand {

    public ProjectClearCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "clear projects";
    }

    @NotNull
    @Override
    public String description() {
        return "command to clear projects";
    }

    @Override
    public void execute() {
        getServiceLocator().getProjectService().removeAllProjects(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
