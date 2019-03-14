package ru.zagorodnikova.tm.command.data;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.Domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class LoadFromJsonJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load json jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load json jaxb";
    }

    @Override
    public void execute() throws IOException, JAXBException, ClassNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        jaxbUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);

        Domain domain = (Domain) jaxbUnmarshaller.unmarshal(new File("C:\\Users\\zagorodnikova\\IdeaProjects\\TodoList\\fileJaxb.json"));

        System.out.println(domain.getUser());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
