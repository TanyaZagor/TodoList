package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
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
    public void execute() {
        @Nullable final List<Project> projects = getServiceLocator().getProjectService().findAllProjects(getServiceLocator().getSession().getUserId());
        if (!(projects == null || projects.isEmpty())) {
            projects.forEach((v) -> System.out.println("Name: " + v.getName() + ", Description: " + v.getDescription()+ ", Date start: " + v.getDateStart() + ", Date finish: " + v.getDateFinish() + ", Date create: " + v.getDateCreate() + ", Status: " + v.getStatus()));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
