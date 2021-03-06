package ru.zagorodnikova.tm.command.project;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.ProjectDto;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

@Component
public class ProjectCreateCommand extends AbstractCommand {

    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

    @NotNull
    @Override
    public String command() {
        return "create project";
    }

    @NotNull
    @Override
    public String description() {
        return "command to create project";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        System.out.println("project description");
        @NotNull final String description = terminalService.nextLine();
        System.out.println("project date start");
        @NotNull final String dateStart = terminalService.nextLine();
        System.out.println("project date finish");
        @NotNull final String dateFinish = terminalService.nextLine();
        @Nullable final ProjectDto project = projectService.persistProject(serviceLocator.getSession(), projectName, description, dateStart, dateFinish);
        if (project != null) {
            System.out.println(project.getName());
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
