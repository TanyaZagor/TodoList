package ru.zagorodnikova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.Domain;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.io.File;
import java.io.IOException;

public class SaveToJsonCommand extends AbstractCommand {
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
        domain.setProjects(getServiceLocator().getProjectService().findAllProjects(getServiceLocator().getSession().getUserId()));
        domain.setTasks(getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getSession().getUserId()));
        File file = new File("fileFasterXml.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
