package ru.zagorodnikova.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import java.io.IOException;


public abstract class AbstractCommand {

    @NotNull
    abstract public String command();

    @NotNull
    abstract public String description();

    abstract public void execute() throws Exception;

    abstract public boolean isSecure();

}
