package ru.zagorodnikova.tm.command.data;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.Domain;

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
        File file = new File("C:\\Users\\zagorodnikova\\IdeaProjects\\TodoList\\fileFasterXml.xml");
        XmlMapper xmlMapper = new XmlMapper();
        Domain domain = xmlMapper.readValue(file, Domain.class);
        System.out.println(domain.getUser());
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
