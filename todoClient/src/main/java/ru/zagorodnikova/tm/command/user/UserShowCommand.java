package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;


public class UserShowCommand extends AbstractCommand {
    public UserShowCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "show user";
    }

    @NotNull
    @Override
    public String description() {
        return "command to show user data";
    }

    @Override
    public void execute() {
        System.out.println(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
