package ru.zagorodnikova.tm.command.system;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String command() {
        return "help";
    }

    @Override
    public String description() {
        return "commands help";
    }

    @Override
    public void execute() {
        getServiceLocator().getCommands().forEach((k, v) -> System.out.println(k + ": " + v.description()));
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
