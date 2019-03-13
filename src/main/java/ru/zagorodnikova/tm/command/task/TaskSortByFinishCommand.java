package ru.zagorodnikova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;
import java.util.Scanner;

public class TaskSortByFinishCommand extends AbstractCommand {

    private Scanner in;

    @NotNull
    @Override
    public String command() {
        return "task sort finish";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sort tasks by date finish";
    }

    @Override
    public void execute() {
        in = getServiceLocator().getScanner();
        System.out.println("project name");
        String projectName = in.nextLine();
        List<AbstractEntity> tasks = getServiceLocator().getTaskService().sortByDateFinish(getServiceLocator().getCurrentUser().getId(), projectName);
        if (tasks!= null) {
            tasks.forEach(System.out::println);
        }
    }

    @NotNull
    @Override
    public boolean isSecure() {
        return true;
    }
}
