package ru.zagorodnikova.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import java.io.IOException;


public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    @NotNull
    abstract public String command();

    @NotNull
    abstract public String description();

    abstract public void execute() throws Exception;

    abstract public boolean isSecure();

    public void setServiceLocator(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}
