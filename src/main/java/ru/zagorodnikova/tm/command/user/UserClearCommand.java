package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserClearCommand extends AbstractCommand {
    public UserClearCommand() {
    }

    @Override
    public String command() {
        return "clear users";
    }

    @Override
    public String description() {
        return "command to clear all users";
    }

    @Override
    public void execute() {
        getServiceLocator().getUserService().removeAll(getServiceLocator().getCurrentUser().getId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
