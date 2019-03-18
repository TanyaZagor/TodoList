package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    @NotNull
    abstract public String command();

    @NotNull
    abstract public String description();

    abstract public void execute() throws IOException, JAXBException, ClassNotFoundException;

    abstract public boolean isSecure();

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}
