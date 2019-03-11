package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserShowCommand extends AbstractCommand {
    public UserShowCommand() {
    }

    @Override
    public String command() {
        return "show user";
    }

    @Override
    public String description() {
        return "command to show user data";
    }

    @Override
    public void execute() {
        System.out.println(getServiceLocator().getCurrentUser());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
