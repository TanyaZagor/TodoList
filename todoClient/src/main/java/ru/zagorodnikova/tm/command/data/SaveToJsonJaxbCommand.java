package ru.zagorodnikova.tm.command.data;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.Domain;
import ru.zagorodnikova.tm.command.AbstractCommand;

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
        domain.setProjects(getServiceLocator().getProjectService().findAllProjects(getServiceLocator().getSession().getUserId()));
        domain.setTasks(getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getSession().getUserId()));
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
