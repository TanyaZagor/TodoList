package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

@Component
public class ProjectRemoveCommand extends AbstractCommand {

    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        projectService.removeProject(serviceLocator.getSession(), projectName);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
