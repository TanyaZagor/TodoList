package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;


public class LoadFromJsonJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load json jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load json jaxb";
    }

    @Override
    public void execute() throws Exception {
        getServiceLocator().getAdminService().loadFromJsonJaxb(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
