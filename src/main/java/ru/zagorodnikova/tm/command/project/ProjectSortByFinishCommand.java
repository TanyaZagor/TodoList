package ru.zagorodnikova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public class ProjectSortByFinishCommand extends AbstractCommand {

    @NotNull
    @Override
    public String command() {
        return "project sort finish";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort task by date finish";
    }

    @Override
    public void execute() {
        @Nullable final List<AbstractEntity> list = getServiceLocator().getProjectService().sortByDateFinish(getServiceLocator().getCurrentUser().getId());
        if (list!= null) {
            list.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
