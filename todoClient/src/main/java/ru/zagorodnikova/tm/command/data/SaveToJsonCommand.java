package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class SaveToJsonCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "save json";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save json fasterxml";
    }

    @Override
    public void execute() {
        getServiceLocator().getAdminService().saveToJson(getServiceLocator().getSession());

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
