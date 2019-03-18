package ru.zagorodnikova.tm.command.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.Domain;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.io.File;
import java.io.IOException;

public class LoadFromJsonCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load json";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load from json";
    }

    @Override
    public void execute() throws IOException{
        File file = new File("fileFasterXml.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(file, Domain.class);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Domain domain = mapper.readValue(file, Domain.class);
        System.out.println(domain.getUser());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
