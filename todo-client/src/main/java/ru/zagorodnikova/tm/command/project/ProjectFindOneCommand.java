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
public class ProjectFindOneCommand extends AbstractCommand {


    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("project name");
        @NotNull final String projectName = terminalService.nextLine();
        @Nullable final ProjectDto project = projectService.findOneProject(serviceLocator.getSession(), projectName);
        if (project != null) {
            System.out.println("Name: " + project.getName() + ", Description: " + project.getDescription()+
                    ", Date start: " + project.getDateStart() + ", Date finish: " + project.getDateFinish() +
                    ", Date create: " + project.getDateCreate() + ", Status: " + project.getStatus().toString());
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
