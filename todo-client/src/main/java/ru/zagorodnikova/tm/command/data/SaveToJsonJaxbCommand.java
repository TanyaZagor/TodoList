package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class SaveToJsonJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save json jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save json jaxb";
    }

    @Override
    public void execute() throws Exception {
        getServiceLocator().getAdminService().saveToJsonJaxb(getServiceLocator().getSession());


    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
