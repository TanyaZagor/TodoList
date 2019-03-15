package ru.zagorodnikova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Domain;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;

import java.io.File;
import java.io.IOException;

public class SaveToJsonCommand extends AbstractCommand{
    @NotNull
    @Override
    public String command() {
        return "save json";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save json fasterxml";
    }

    @Override
    public void execute() throws IOException {
        final Domain domain = new Domain();
        domain.setUser(getServiceLocator().getCurrentUser());

        for (AbstractEntity project : getServiceLocator().getProjectService().findAll(getServiceLocator().getCurrentUser().getId())) {
            domain.getProjects().add((Project) project);
        }

        for (AbstractEntity task : getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getCurrentUser().getId())) {
            domain.getTasks().add((Task) task);
        }
        File file = new File("fileFasterXml.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
