package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Domain;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;

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
        domain.setProjects(getServiceLocator().getProjectService().findAll(getServiceLocator().getCurrentUser().getId()));
        domain.setTasks(getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getCurrentUser().getId()));
        File file = new File("file.txt");
        domain.setUser(getServiceLocator().getCurrentUser());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
