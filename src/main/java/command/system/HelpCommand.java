package command.system;

import command.AbstractCommand;
import bootstrap.Bootstrap;

public class HelpCommand extends AbstractCommand {

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
        Bootstrap.commands.forEach((k, v) -> System.out.println(k + ": " + v.description()));
    }
}
