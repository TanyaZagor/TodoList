package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.inject.Inject;

public class UserSignOutCommand extends AbstractCommand {
    @Inject
    private SessionEndpoint sessionService;

    @Inject
    private ServiceLocator serviceLocator;

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
        sessionService.remove(serviceLocator.getSession());
        serviceLocator.setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
