package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserSignOutCommand extends AbstractCommand {
    public UserSignOutCommand() {
    }

    @Override
    public String command() {
        return "sign out";
    }

    @Override
    public String description() {
        return "command to sign out";
    }

    @Override
    public void execute() {
        getServiceLocator().setCurrentUser(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
