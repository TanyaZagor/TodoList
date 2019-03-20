package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class SaveToXmlJaxbCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save xml jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "save to xml jaxb";
    }

    @Override
    public void execute() {
        getServiceLocator().getAdminService().saveToXmlJaxb(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
