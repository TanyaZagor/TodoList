package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;
import java.util.Scanner;

public class ProjectSortByCreateCommand extends AbstractCommand {

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
    public void execute() {
        @Nullable final List<Project> list = getServiceLocator().getProjectService().sortByDateCreated(getServiceLocator().getCurrentUser().getId());
        if (list!= null) {
            list.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
