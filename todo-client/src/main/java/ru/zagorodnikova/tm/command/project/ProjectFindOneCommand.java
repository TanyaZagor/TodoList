package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.Project;

public class ProjectFindOneCommand extends AbstractCommand {

    public ProjectFindOneCommand() {
    }

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
    public void execute() throws Exception_Exception {
        System.out.println("project name");
        @NotNull final String projectName = getServiceLocator().getTerminalService().nextLine();
        @Nullable final Project project = getServiceLocator().getProjectService().findOneProject(getServiceLocator().getSession(), projectName);
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
