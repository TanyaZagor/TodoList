package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

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
    public void execute() {
        getServiceLocator().getAdminService().save(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
