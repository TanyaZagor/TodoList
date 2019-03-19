package ru.zagorodnikova.tm.command.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.Domain;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.io.File;
import java.io.IOException;

public class LoadFromXmlCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load xml";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load from xml";
    }

    @Override
    public void execute() throws IOException{
        File file = new File("fileFasterXml.xml");
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Domain domain = xmlMapper.readValue(file, Domain.class);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
