package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class LoadFromXmlJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load xml jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load xml jaxb";
    }

    @Override
    public void execute() throws Exception {
        getServiceLocator().getAdminService().loadFromXmlJaxb(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
