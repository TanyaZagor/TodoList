package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.Domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save data";
    }

    @Override
    public void execute() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data.txt"));
        final Domain domain = new Domain();
        //domain.setProjects(getServiceLocator().getProjectService().findAll(getServiceLocator().getCurrentUser().getId()));
        //domain.setTasks(getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getCurrentUser().getId()));
        domain.setUser(getServiceLocator().getCurrentUser());
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
