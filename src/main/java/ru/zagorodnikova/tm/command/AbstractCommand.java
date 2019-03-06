package ru.zagorodnikova.tm.command;

import ru.zagorodnikova.tm.api.ServiceLocator;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    public AbstractCommand(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    abstract public String command();
    abstract public String description();
    abstract public void execute();

    abstract public boolean isSecure();
}
