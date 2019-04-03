package ru.zagorodnikova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

import javax.inject.Inject;

public class HelpCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "help";
    }

    @NotNull
    @Override
    public String description() {
        return "commands help";
    }

    @Override
    public void execute() {
        serviceLocator.getCommands().forEach((k, v) -> System.out.println(k + ": " + v.description()));
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
