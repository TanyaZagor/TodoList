package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class SaveCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save data";
    }

    @Override
    public void execute() throws Exception_Exception {
        getServiceLocator().getAdminService().save(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
