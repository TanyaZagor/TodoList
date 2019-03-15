package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.Project;

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
        @Nullable final List<Project> projects = getServiceLocator().getProjectService().findAll(getServiceLocator().getCurrentUser().getId());
        if (!(projects == null || projects.isEmpty())) {
            projects.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
