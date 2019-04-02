package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class UserRemoveCommand extends AbstractCommand {
    public UserRemoveCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "remove user";
    }

    @NotNull
    @Override
    public String description() {
        return "command to remove user";
    }

    @Override
    public void execute() throws Exception {
        getServiceLocator().getUserService().removeUser(getServiceLocator().getSession());
        getServiceLocator().setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
