package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.ProjectDto;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;

import java.util.List;

@Component
public class ProjectListCommand extends AbstractCommand {

    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "project list";
    }

    @NotNull
    @Override
    public String description() {
        return "print out project list";
    }

    @Override
    public void execute() throws Exception {
        @Nullable final List<ProjectDto> projects = projectService.findAllProjects(serviceLocator.getSession());
        if (!(projects == null || projects.isEmpty())) {
            projects.forEach((v) -> System.out.println(v.getName()));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
