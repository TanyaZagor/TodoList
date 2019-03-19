package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.Domain;
import ru.zagorodnikova.tm.command.AbstractCommand;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SaveToXmlJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save xml jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "save to xml jaxb";
    }

    @Override
    public void execute() throws JAXBException {
        final Domain domain = new Domain();
        domain.setProjects(getServiceLocator().getProjectService().findAllProjects(getServiceLocator().getSession().getUserId()));
        domain.setTasks(getServiceLocator().getTaskService().findAllTasks(getServiceLocator().getSession().getUserId()));
        File file = new File("fileJaxb.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(domain, file);
        System.out.println("saved");

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
