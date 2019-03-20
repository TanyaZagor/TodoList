package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Project;

import java.util.List;

public class ProjectSortByStatusCommand extends AbstractCommand {

    @NotNull
    @Override
    public String command() {
        return "project sort status";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort task by status";
    }

    @Override
    public void execute() {
        @Nullable final List<Project> list = getServiceLocator().getProjectService().sortProjectsByStatus(getServiceLocator().getSession());
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
