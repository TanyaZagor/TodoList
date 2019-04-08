package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;

@Component
public class ProjectClearCommand extends AbstractCommand {
    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
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
