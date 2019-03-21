package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class SaveToXmlCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save xml";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save xml";
    }

    @Override
    public void execute() throws Exception_Exception {
        getServiceLocator().getAdminService().saveToXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
