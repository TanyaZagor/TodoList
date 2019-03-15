package ru.zagorodnikova.tm.command.data;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Domain;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SaveToJsonJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save json jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save json jaxb";
    }

    @Override
    public void execute() throws JAXBException {
        final Domain domain = new Domain();
        domain.setUser(getServiceLocator().getCurrentUser());
        domain.setProjects(getServiceLocator().getProjectService().findAll(getServiceLocator().getCurrentUser().getId()));
        domain.setTasks(getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getCurrentUser().getId()));
        File file = new File("fileJaxb.json");
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

        jaxbMarshaller.marshal(domain, file);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
