package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.AdminEndpoint;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;

import javax.inject.Inject;


public class ProjectClearCommand extends AbstractCommand {
    @Inject
    private ProjectEndpoint projectService;

    @Inject
    private ServiceLocator serviceLocator;

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
    public void execute() throws Exception {
        projectService.removeAllProjects(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
