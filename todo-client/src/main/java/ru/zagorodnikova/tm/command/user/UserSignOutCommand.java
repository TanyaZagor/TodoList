package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class UserSignOutCommand extends AbstractCommand {
    public UserSignOutCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "sign out";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sign out";
    }

    @Override
    public void execute() throws Exception {
        getServiceLocator().getSessionService().remove(getServiceLocator().getSession());
        getServiceLocator().setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
