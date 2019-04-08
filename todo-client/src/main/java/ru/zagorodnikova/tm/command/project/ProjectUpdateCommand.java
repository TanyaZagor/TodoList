package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

@Component
public class ProjectUpdateCommand extends AbstractCommand {

    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String oldProjectName = terminalService.nextLine();
        System.out.println("New project name");
        @NotNull final String projectName = terminalService.nextLine();
        System.out.println("New description");
        @NotNull final String description = terminalService.nextLine();
        System.out.println("New date start");
        @NotNull final String dateStart = terminalService.nextLine();
        System.out.println("New date finish");
        @NotNull final String dateFinish = terminalService.nextLine();
        System.out.println("Status");
        @NotNull final String status = terminalService.nextLine();
        projectService.mergeProject(serviceLocator.getSession(), oldProjectName, projectName, description, dateStart, dateFinish, status);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
