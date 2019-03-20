package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class LoadFromJsonCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load json";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load from json";
    }

    @Override
    public void execute(){
        getServiceLocator().getAdminService().loadFromJson(getServiceLocator().getSession());

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
