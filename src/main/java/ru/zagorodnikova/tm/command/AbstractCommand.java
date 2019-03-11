package ru.zagorodnikova.tm.command;
import org.jetbrains.annotations.NotNull;

import ru.zagorodnikova.tm.api.ServiceLocator;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    public AbstractCommand() {

    }


    @NotNull
    abstract public String command();

    @NotNull
    abstract public String description();

    abstract public void execute();

    @NotNull
    abstract public boolean isSecure();

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}
