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
public class ProjectSortByCreateCommand extends AbstractCommand {
    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "project sort create";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort task by date created";
    }

    @Override
    public void execute() throws Exception {
        @Nullable final List<ProjectDto> list = projectService.sortProjectsByDateCreated(serviceLocator.getSession());
        if (list!= null) {
            list.forEach((project) -> System.out.println("Name: " + project.getName() +
                    ", Description: " + project.getDescription()+ ", Date start: " + project.getDateStart() +
                    ", Date finish: " + project.getDateFinish() + ", Date create: " + project.getDateCreate() +
                    ", Status: " + project.getStatus()));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
