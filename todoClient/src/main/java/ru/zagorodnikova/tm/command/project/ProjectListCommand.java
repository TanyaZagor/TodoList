package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.Project;

import java.util.List;

public class ProjectListCommand extends AbstractCommand {

    public ProjectListCommand() {
    }

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
    public void execute() throws Exception_Exception {
        @Nullable final List<Project> projects = getServiceLocator().getProjectService().findAllProjects(getServiceLocator().getSession());
        if (!(projects == null || projects.isEmpty())) {
            projects.forEach((v) -> System.out.println(v.getName()));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
