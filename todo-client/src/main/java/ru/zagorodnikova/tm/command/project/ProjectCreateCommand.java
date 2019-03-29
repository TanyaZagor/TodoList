package ru.zagorodnikova.tm.command.project;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.ProjectDto;

public class ProjectCreateCommand extends AbstractCommand {

    public ProjectCreateCommand() {
    }

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
    public void execute() throws Exception_Exception {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("project description");
        @NotNull final String description = getServiceLocator().getTerminalService().nextLine();
        System.out.println("project date start");
        @NotNull final String dateStart = getServiceLocator().getTerminalService().nextLine();
        System.out.println("project date finish");
        @NotNull final String dateFinish = getServiceLocator().getTerminalService().nextLine();
        @Nullable final ProjectDto project = getServiceLocator().getProjectService().persistProject(getServiceLocator().getSession(), projectName, description, dateStart, dateFinish);
        if (project != null) {
            System.out.println(project.getName());
        }

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
