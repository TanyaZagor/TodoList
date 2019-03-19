package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.Domain;
import ru.zagorodnikova.tm.command.AbstractCommand;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LoadFromXmlJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load xml jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load xml jaxb";
    }

    @Override
    public void execute() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Domain domain = (Domain) jaxbUnmarshaller.unmarshal(new File("fileJaxb.xml"));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
