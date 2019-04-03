package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.inject.Inject;

public class UserRemoveCommand extends AbstractCommand {
    @Inject
    private UserEndpoint userService;

    @Inject
    private ServiceLocator serviceLocator;

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
        userService.removeUser(serviceLocator.getSession());
        serviceLocator.setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
