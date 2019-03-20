package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

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
    public void execute() {
        getServiceLocator().getAdminService().saveToJsonJaxb(getServiceLocator().getSession());


    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
