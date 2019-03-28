package ru.zagorodnikova.tm.command.admin;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

public class AdminRemoveUsersCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "remove all users";
    }

    @NotNull
    @Override
    public String description() {
        return "command to remove all users";
    }

    @Override
    public void execute() throws Exception_Exception {
        getServiceLocator().getAdminService().removeAllUsers(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
