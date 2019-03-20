package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class LoadCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load";
    }

    @Override
    public void execute() {
        getServiceLocator().getAdminService().load(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
