package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.Project;

import java.util.List;

public class ProjectSortByStartCommand extends AbstractCommand {

    @NotNull
    @Override
    public String command() {
        return "project sort start";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort task by date start";
    }

    @Override
    public void execute() throws Exception_Exception {
        @Nullable final List<Project> list = getServiceLocator().getProjectService().sortProjectsByDateStart(getServiceLocator().getSession());
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
