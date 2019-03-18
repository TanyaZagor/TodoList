package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

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
    public void execute() {
        getServiceLocator().getUserService().removeUser(getServiceLocator().getCurrentUser().getId());
        getServiceLocator().setCurrentUser(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
