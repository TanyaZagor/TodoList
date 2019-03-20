package ru.zagorodnikova.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    @NotNull
    abstract public String command();

    @NotNull
    abstract public String description();

    abstract public void execute();

    abstract public boolean isSecure();

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}
