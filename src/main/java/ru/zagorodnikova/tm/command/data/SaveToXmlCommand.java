package ru.zagorodnikova.tm.command.data;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Domain;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public class SaveToXmlCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save xml";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save xml";
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
        File file = new File("fileFasterXml.xml");
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
