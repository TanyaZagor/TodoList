package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

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
    public void execute() {
        getServiceLocator().setCurrentUser(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
