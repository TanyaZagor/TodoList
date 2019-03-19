package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserClearCommand extends AbstractCommand {
    public UserClearCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "clear users";
    }

    @NotNull
    @Override
    public String description() {
        return "command to clear all users";
    }

    @Override
    public void execute() {
        getServiceLocator().getUserService().removeAllUsers(getServiceLocator().getSession().getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
