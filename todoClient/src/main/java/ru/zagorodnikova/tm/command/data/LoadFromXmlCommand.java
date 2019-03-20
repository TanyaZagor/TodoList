package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class LoadFromXmlCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load xml";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load from xml";
    }

    @Override
    public void execute(){
        getServiceLocator().getAdminService().loadFromXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
