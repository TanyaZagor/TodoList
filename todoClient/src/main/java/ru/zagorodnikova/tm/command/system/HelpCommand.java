package ru.zagorodnikova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
    }

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
        getServiceLocator().getCommands().forEach((k, v) -> System.out.println(k + ": " + v.description()));
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
