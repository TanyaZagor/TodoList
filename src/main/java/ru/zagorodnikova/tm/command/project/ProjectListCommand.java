package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.List;

public class ProjectListCommand extends AbstractCommand {

    public ProjectListCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String command() {
        return "project list";
    }

    @Override
    public String description() {
        return "print out project list";
    }

    @Override
    public void execute() {

        List<Project> projects = getServiceLocator().getProjectService().findAll(getServiceLocator().getCurrentUser().getId());

        if (!(projects == null || projects.isEmpty())) {
            projects.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
