package ru.zagorodnikova.tm.command.system;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class HelpCommand extends AbstractCommand {
    private Bootstrap bootstrap;

    public HelpCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        bootstrap.commands.forEach((k, v) -> System.out.println(k + ": " + v.description()));
    }
}
