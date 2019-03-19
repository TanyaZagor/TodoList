package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.Domain;
import ru.zagorodnikova.tm.command.AbstractCommand;

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

        final Domain domain = new Domain();
        domain.setProjects(getServiceLocator().getProjectService().findAllProjects(getServiceLocator().getSession().getUserId()));
        domain.setTasks(getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getSession().getUserId()));
        File file = new File("file.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
